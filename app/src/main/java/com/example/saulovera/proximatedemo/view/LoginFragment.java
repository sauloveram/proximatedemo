package com.example.saulovera.proximatedemo.view;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.saulovera.proximatedemo.MainActivity;
import com.example.saulovera.proximatedemo.R;
import com.example.saulovera.proximatedemo.dao.ProfileEntity;
import com.example.saulovera.proximatedemo.dao.ProfileEntityDao;
import com.example.saulovera.proximatedemo.dao.SectionEntityDao;
import com.example.saulovera.proximatedemo.vo.LoginRequest;
import com.example.saulovera.proximatedemo.vo.LoginResponse;
import com.example.saulovera.proximatedemo.vo.ProfileSection;
import com.example.saulovera.proximatedemo.vo.UserResponse;
import com.example.saulovera.proximatedemo.wsclient.HTTPSWSClient;
import com.example.saulovera.proximatedemo.wsclient.ServiceCallback;
import com.example.saulovera.proximatedemo.wsclient.URL_SERVICE;
import com.example.saulovera.proximatedemo.wsclient.WSAsyncTask;
import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by saulovera on 24/8/2017.
 */

public class LoginFragment extends Fragment {

    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.login_fragment, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView.findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText inputMail = (EditText) rootView.findViewById(R.id.login_user_input);
                EditText inputPass = (EditText) rootView.findViewById(R.id.login_pass_input);
                final TextInputLayout tilMail = (TextInputLayout) rootView.findViewById(R.id.login_user_il);
                final TextInputLayout tilpass = (TextInputLayout) rootView.findViewById(R.id.login_pass_il);
                tilMail.setError(null);
                tilpass.setError(null);
                String mail = inputMail.getText().toString().trim();
                String pass = inputPass.getText().toString().trim();


                if (mail.length() == 0) {
                    tilMail.setError("El usuario es requerido");
                    inputMail.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

                    return;
                } else if (!validateRegExField(mail, REGEX_MAIL)) {
                    tilMail.setError("El usuario tiene un formato incorrecto");
                    inputMail.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

                    return;
                }

                if (pass.length() < 1) {
                    tilpass.setError("La contraseña no puede estar vacía");
                    inputPass.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

                    return;
                } else if (inputPass.getText().toString().contains(" ")) {
                    tilpass.setError("La contraseña no puede contener espacios");
                    inputPass.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

                    return;
                } else if (pass.length() < 8) {
                    tilpass.setError("La contraseña no puede ser menor a 8 caracteres");
                    inputPass.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

                    return;
                }

                LoginRequest request = new LoginRequest();
                request.setCorreo(mail);
                request.setContrasenia(pass);

                WSAsyncTask task = new WSAsyncTask(URL_SERVICE.USER_LOGIN, HTTPSWSClient.POST, new Gson().toJson(request));
                task.setmCallback(mcallback);
                task.execute();
                showLoading();

            }
        });

    }

    private String token = "";

    private ServiceCallback mcallback = new ServiceCallback() {
        @Override
        public void onServiceCallback(Object o) {
            if (isAdded()) {
                try {


                    Gson gson = new Gson();
                    LoginResponse loginResponse = gson.fromJson((String) o, LoginResponse.class);

                    if (loginResponse.getSuccess().equals("true")) {
                        token = loginResponse.getToken();
                        WSAsyncTask task = new WSAsyncTask(URL_SERVICE.USER_DATA, HTTPSWSClient.POST, new Gson().toJson(loginResponse.getToken()));
                        task.setmCallback(mcallback);
                        task.execute();
                    } else {
                        new AlertDialog.Builder(getActivity())
                                .setTitle("Alerta!")
                                .setIcon(0)
                                .setMessage(loginResponse.getMessage())
                                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Alerta!")
                            .setIcon(0)
                            .setMessage("Ocurrio un problema")
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
            }
        }
    };

    private ServiceCallback mcallbackProfile = new ServiceCallback() {
        @Override
        public void onServiceCallback(Object o) {

            try {
                Gson gson = new Gson();
                UserResponse loginResponse = gson.fromJson((String) o, UserResponse.class);

                if (loginResponse.getSuccess().equals("true")) {
                   saveUser(loginResponse);
                } else {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Alerta!")
                            .setIcon(0)
                            .setMessage(loginResponse.getMessage())
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new AlertDialog.Builder(getActivity())
                        .setTitle("Alerta!")
                        .setIcon(0)
                        .setMessage("Ocurrio un problema")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }

        }
    };

    private void saveUser(UserResponse userResponse){
        if(isAdded()){
            MainActivity activity = (MainActivity)getActivity();
            ProfileEntityDao profileEntityDao = activity.getDaoSession().getProfileEntityDao();
            profileEntityDao.deleteAll();
            SectionEntityDao sectionEntityDao = activity.getDaoSession().getSectionEntityDao();
            sectionEntityDao.deleteAll();

            ProfileEntity profileEntity = new ProfileEntity();
            profileEntity.setCorreo(userResponse.getData().get(0).getCorreo());
            profileEntity.setActivo(userResponse.getData().get(0).getEstados_usuarios_label());
            profileEntity.setApellidos(userResponse.getData().get(0).getApellidos());
            profileEntity.setDocumentos_id(userResponse.getData().get(0).getDocumentos_id());
            profileEntity.setEliminado(userResponse.getData().get(0).getEliminado());
            profileEntity.setDocumentos_label(userResponse.getData().get(0).getDocumentos_label());
            profileEntity.setId_server(userResponse.getData().get(0).getId());
            profileEntity.setNumero_documento(userResponse.getData().get(0).getNumero_documento());
            profileEntity.setToken(token);
            profileEntity.setUltima_sesion(userResponse.getData().get(0).getUltima_sesion());
            profileEntity.setName(userResponse.getData().get(0).getNombres());

            for (ProfileSection profileSection: userResponse.getData().get(0).getSecciones() ){
                
            }

        }
    }


    private String REGEX_MAIL = "^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,25}$";

    private static boolean validateRegExField(String input, String regularExpresion) {
        Pattern pattern = Pattern.compile(regularExpresion);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private void dissmisLoading() {
        if (isAdded()) {
            if (rootView.findViewById(R.id.progressContent).getVisibility() == View.VISIBLE) {
                rootView.findViewById(R.id.progressContent).animate().alpha(0f).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        rootView.findViewById(R.id.progressContent).setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        rootView.findViewById(R.id.progressContent).setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                });

            }
        }
    }


    private void showLoading() {
        if (isAdded()) {
            if (rootView.findViewById(R.id.progressContent).getVisibility() == View.GONE) {
                rootView.findViewById(R.id.progressContent).setAlpha(0f);
                rootView.findViewById(R.id.progressContent).animate().alpha(1f).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        rootView.findViewById(R.id.progressContent).setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        rootView.findViewById(R.id.progressContent).setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                });

            }
        }
    }
}
