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

import com.example.saulovera.proximatedemo.R;
import com.example.saulovera.proximatedemo.vo.LoginRequest;
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
                task.setmCallback(new ServiceCallback() {
                    @Override
                    public void onServiceCallback(Object o) {

                        if (isAdded()) {
                            dissmisLoading();
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("Alerta!")
                                    .setIcon(0)
                                    .setMessage((String) o)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(final DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).show();

                        }
                    }
                });
                task.execute();
                showLoading();

            }
        });

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
