//package com.yedam.app.yedam_admin.service.impl;
//
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.opencsv.CSVReader;
//import com.yedam.app.yedam_admin.service.AdminService;
//import com.yedam.app.yedam_admin.service.CsvFileVO;
//
//public class AdminServiceImpl implements AdminService {
//
//	@Override
//	public List<CsvFileVO> readCsv(String filePath) {
//        List<CsvFileVO> csvFile = new ArrayList<>();
//        
//        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
//            String[] line;
//            reader.readNext(); // Skip header
//            while ((line = reader.readNext()) != null) {
//                CsvFileVO csvFileVO = new CsvFileVO();
//                csvFileVO.setNum(Long.parseLong(line[0]));
//                csvFileVO.setName(line[1]);
//                csvFileVO.setPhone(Integer.parseInt(line[2]));
//                csvFile.add(csvFileVO);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return csvFile;
//    }
//
//}
