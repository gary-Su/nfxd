package com.edu.nfxd.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.edu.nfxd.model.Student;
import com.edu.nfxd.service.StudentService;

@Controller
public class ExcelController {
	
//	创建一个excel文件工作薄；（HSSFWorkbook workbook = new HSSFWorkbook()）
//	创建一张表；HSSFSheet sheet = workbook.createSheet("统计表")
//	创建一行；HSSFRow row = sheet.createRow(0)
//	填充一列数据; row.createCell(0).setCellValue("数据")
//	设置一个单元格样式；cell.setCellStyle(style)
	@Autowired
	public StudentService service;
	//创建表头
    private void createTitle(HSSFWorkbook workbook,HSSFSheet sheet){
    	//第一行设置标题
    	 HSSFRow row0 = sheet.createRow(0);
    	 HSSFCell cell0 = row0.createCell(0);
    	  //设置为居中加粗
          HSSFCellStyle style0 = workbook.createCellStyle();
          HSSFFont font0 = workbook.createFont();
          font0.setFontHeightInPoints((short) 18);
          font0.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
          font0.setBold(true);
          font0.setFontName("宋体");
          style0.setAlignment(HSSFCellStyle.ALIGN_CENTER);
          style0.setFont(font0);
          sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
          cell0.setCellValue("学生报名信息");
          cell0.setCellStyle(style0);
        
          
    	 HSSFRow row = sheet.createRow(1);
//    	 自动列宽
//    	  sheet.autoSizeColumn(9, true);
         //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
//         sheet.setColumnWidth(1,12*256);
    	 sheet.setColumnWidth(0,8*256);
    	 sheet.setColumnWidth(1,18*256);
    	 sheet.setColumnWidth(2,8*256);
    	 sheet.setColumnWidth(3,18*256);
    	 sheet.setColumnWidth(4,18*256);
    	 sheet.setColumnWidth(5,25*256);
    	 sheet.setColumnWidth(6,10*256);
    	 sheet.setColumnWidth(7,10*256);
    	 sheet.setColumnWidth(8,14*256);
    	 sheet.setColumnWidth(9,18*256);
         //设置为居中加粗
         HSSFCellStyle style = workbook.createCellStyle();
         HSSFFont font = workbook.createFont();
         font.setBold(true);
         style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         style.setFont(font);
         
         HSSFCell cell;
         cell = row.createCell(0);
         cell.setCellValue("序号");
         cell.setCellStyle(style);
  
  
         cell = row.createCell(1);
         cell.setCellValue("学生姓名");
         cell.setCellStyle(style);
  
         cell = row.createCell(2);
         cell.setCellValue("性别");
         cell.setCellStyle(style);
  
         cell = row.createCell(3);
         cell.setCellValue("手机号码");
         cell.setCellStyle(style);
         
         cell = row.createCell(4);
         cell.setCellValue("QQ或微信");
         cell.setCellStyle(style);
         
         cell = row.createCell(5);
         cell.setCellValue("高中毕业院校");
         cell.setCellStyle(style);
         
         cell = row.createCell(6);
         cell.setCellValue("高中分科");
         cell.setCellStyle(style);
         
         cell = row.createCell(7);
         cell.setCellValue("高考分数");
         cell.setCellStyle(style);
         
         cell = row.createCell(8);
         cell.setCellValue("意向专业");
         cell.setCellStyle(style);
         
         cell = row.createCell(9);
         cell.setCellValue("投递时间");
         cell.setCellStyle(style);
    }
	 //生成user表excel
    @GetMapping(value = "/export")
    public void getUser(HttpServletResponse response){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("统计表");
        createTitle(workbook,sheet);
        List<Student> rows = service.selectAll();
 
        //设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
 
        //新增数据行，并且设置单元格数据
        int rowNum=2;
        for(Student stu:rows){
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(stu.getId());
            row.createCell(1).setCellValue(stu.getName());
            row.createCell(2).setCellValue(stu.getSex());
            row.createCell(3).setCellValue(stu.getPhone());
            row.createCell(4).setCellValue(stu.getQqorwechat());
            row.createCell(5).setCellValue(stu.getSchool());
            row.createCell(6).setCellValue(stu.getSubject());
            row.createCell(7).setCellValue(stu.getGrade());
            row.createCell(8).setCellValue(stu.getMajor());
           // row.createCell(9).setCellValue(stu.getCreateTime());
            HSSFCell cell = row.createCell(9);
            cell.setCellValue(stu.getCreateTime());
            cell.setCellStyle(style);
            rowNum++;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(new Date());
        String fileName = "学生报名信息"+date+".xls";
 
        //生成excel文件
//        buildExcelFile(fileName, workbook);
 
        //浏览器下载excel
        buildExcelDocument(fileName,workbook,response);
 
//        return "download excel";
    }
 
    //生成excel文件
    protected void buildExcelFile(String filename,HSSFWorkbook workbook){
        FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filename);
			workbook.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
       
    }
 
    //浏览器下载excel
    protected void buildExcelDocument(String filename,HSSFWorkbook workbook,HttpServletResponse response){
    	 OutputStream outputStream = null;
    	 try {
    		 response.setContentType("application/vnd.ms-excel");
			 response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename, "utf-8"));
			 outputStream = response.getOutputStream();
		     workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 try {
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
      
    }

}
