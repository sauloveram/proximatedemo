package com.example.saulovera.proximatedemo.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.saulovera.proximatedemo.dao.ProfileEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PROFILE_ENTITY".
*/
public class ProfileEntityDao extends AbstractDao<ProfileEntity, Long> {

    public static final String TABLENAME = "PROFILE_ENTITY";

    /**
     * Properties of entity ProfileEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Lastname = new Property(2, String.class, "lastname", false, "LASTNAME");
        public final static Property Secondname = new Property(3, String.class, "secondname", false, "SECONDNAME");
        public final static Property Telephone = new Property(4, String.class, "telephone", false, "TELEPHONE");
        public final static Property ZipCode = new Property(5, String.class, "zipCode", false, "ZIP_CODE");
        public final static Property LatPoint = new Property(6, Double.class, "latPoint", false, "LAT_POINT");
        public final static Property LonPoint = new Property(7, Double.class, "lonPoint", false, "LON_POINT");
    };


    public ProfileEntityDao(DaoConfig config) {
        super(config);
    }
    
    public ProfileEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PROFILE_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT NOT NULL ," + // 1: name
                "\"LASTNAME\" TEXT," + // 2: lastname
                "\"SECONDNAME\" TEXT," + // 3: secondname
                "\"TELEPHONE\" TEXT," + // 4: telephone
                "\"ZIP_CODE\" TEXT," + // 5: zipCode
                "\"LAT_POINT\" REAL," + // 6: latPoint
                "\"LON_POINT\" REAL);"); // 7: lonPoint
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PROFILE_ENTITY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ProfileEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
 
        String lastname = entity.getLastname();
        if (lastname != null) {
            stmt.bindString(3, lastname);
        }
 
        String secondname = entity.getSecondname();
        if (secondname != null) {
            stmt.bindString(4, secondname);
        }
 
        String telephone = entity.getTelephone();
        if (telephone != null) {
            stmt.bindString(5, telephone);
        }
 
        String zipCode = entity.getZipCode();
        if (zipCode != null) {
            stmt.bindString(6, zipCode);
        }
 
        Double latPoint = entity.getLatPoint();
        if (latPoint != null) {
            stmt.bindDouble(7, latPoint);
        }
 
        Double lonPoint = entity.getLonPoint();
        if (lonPoint != null) {
            stmt.bindDouble(8, lonPoint);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public ProfileEntity readEntity(Cursor cursor, int offset) {
        ProfileEntity entity = new ProfileEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // lastname
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // secondname
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // telephone
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // zipCode
            cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6), // latPoint
            cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7) // lonPoint
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ProfileEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setLastname(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSecondname(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTelephone(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setZipCode(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setLatPoint(cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6));
        entity.setLonPoint(cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ProfileEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(ProfileEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
