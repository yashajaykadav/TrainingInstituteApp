package com.nexanova.user_service.util;

import com.nexanova.user_service.entity.User;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class CSVHelper {

    public static List<User> parseUserService(MultipartFile file){

        try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            CsvToBean<User> csvToBean = new CsvToBeanBuilder<User>(reader)
                    .withType(User.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return  csvToBean.parse();
        } catch (Exception e) {
            throw new RuntimeException("Failed TO Parse File "+e.getMessage());
        }
    }
}
