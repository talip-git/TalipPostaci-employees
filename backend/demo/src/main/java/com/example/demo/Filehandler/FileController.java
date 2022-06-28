package com.example.demo.Filehandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Filehandler.models.Calculated;
import com.example.demo.Filehandler.models.Row;
import com.example.demo.Filehandler.utils.FileUtils;

@RestController
@RequestMapping(path="/api/filehandler")
public class FileController {
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping 
    public List<Calculated> handleFile(@RequestBody MultipartFile file){
        try {
            InputStream stream = file.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            List<Row> list = new ArrayList<>();
            String line = "";
            while((line = reader.readLine()) != null){
                Row re = FileUtils.parseLine(line);
                list.add(re);
            }
            return FileUtils.calculate(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    } 
}
