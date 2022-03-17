package mz.co.syrah.gestao.domain.service.export;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import mz.co.syrah.gestao.domain.model.CategoriaEntrada;
import mz.co.syrah.gestao.domain.repository.CategoriaEntradaRepository;
import mz.co.syrah.gestao.domain.repository.export.CategoriaEntradaExportRepository;

@Service
public class CategoriaEntradaExportService implements CategoriaEntradaExportRepository{

	@Override
	public ByteArrayInputStream export(List<CategoriaEntrada> categoriaEntradas) {
		
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("inventario");
			
			Row row = sheet.createRow(0);
			
			// Define header cell style
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        // Creating header cells 
	        Cell cell = row.createCell(0);
	        cell.setCellValue("ID");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(1);
	        cell.setCellValue("Item");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(2);
	        cell.setCellValue("Existencia");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(3);
	        cell.setCellValue("Fornecedor");
	        cell.setCellStyle(headerCellStyle);
	 
	
	        
	        // Creating data rows for each contact
	        for(int i = 0; i < categoriaEntradas.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(categoriaEntradas.get(i).getId());
	        	dataRow.createCell(1).setCellValue(categoriaEntradas.get(i).getNome());
	        	dataRow.createCell(2).setCellValue(categoriaEntradas.get(i).getExistencia());
	        	dataRow.createCell(3).setCellValue(categoriaEntradas.get(i).getFornecedor().getNome());
	        	
	        }
	
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			return null;
		}
	}
}
