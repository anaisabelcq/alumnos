package com.bolivarsoft.repository;

import com.bolivarsoft.model.MetadataMenu;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class LectorMetadataMenu {
    public MetadataMenu leer() {
        try {
            String jsonFile = FileUtils.readFileToString(
                    new File("C:/var/tmp/bolivarsoft/menu.json"),
                    "UTF-8"
            );
            MetadataMenu mm = new Gson().fromJson(jsonFile, MetadataMenu.class);
            return mm;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }
}
