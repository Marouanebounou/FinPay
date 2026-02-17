package org.example.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.example.config.DatabaseConfig;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class RapportMensuel {


    public static void generateExcelRapportMensuel() throws Exception{
        String sql = "select p.nom as prestataire, count(f.id) as nombre_factures, sum(f.balance) as total_genere, sum(f.balance * 0.02) as total_commissions from facture f join prestataire p on f.id_pre = p.id_pre where month(f.date) = ?  and year(f.date) = ?  group by p.nom;";
        Scanner scanner  = new Scanner(System.in);
        System.out.print("Entrer mois : ");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrer annee : ");
        int year = Integer.parseInt(scanner.nextLine());
        Connection connection = DatabaseConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1 , month);
        preparedStatement.setInt(2, year);
        ResultSet resultSet = preparedStatement.executeQuery();

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Rapport Global");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Prestataire");
        header.createCell(1).setCellValue("Nombre Factures");
        header.createCell(2).setCellValue("Total Généré");
        header.createCell(3).setCellValue("Total Commissions");

        int rowNum = 1;

        while (resultSet.next()){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(resultSet.getString("prestataire"));
            row.createCell(1).setCellValue(resultSet.getInt("nombre_factures"));
            row.createCell(2).setCellValue(resultSet.getDouble("total_genere"));
            row.createCell(3).setCellValue(resultSet.getDouble("total_commissions"));
        }

        for (int i = 0 ; i < 4 ; i++){
            sheet.autoSizeColumn(i);
        }

        String fileName = "rapportglobal" + month + ".xls";
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
        connection.close();

        System.out.println("Rapport généré avec succès !");

    }


}
