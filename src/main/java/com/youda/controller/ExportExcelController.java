package com.youda.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.youda.model.User;
import com.youda.service.UserService;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-30
 * @introduce 定义导出excel表的控制器
 */

@RestController
@RequestMapping(value = "/excel")
@CrossOrigin(maxAge = 3600, origins = "*")
public class ExportExcelController {
 
	/**
	 * 实现用户服务自动依赖注入
	 */
	@Autowired
	private UserService userService; 
	
	/**
	 * 创建表格表头
	 * @param workbook
	 * @param sheet
	 */
	private void createTableTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
		// TODO Auto-generated method stub
		HSSFRow row = sheet.createRow(0);
		//设置列宽,setColumnWidth的第二个参数要乘以256,这个参数的单位是1/256个字符宽度
		sheet.setColumnWidth(2, 12*256);
		sheet.setColumnWidth(3, 17*256);
		
		//设置为居中加粗
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setFont(font);
		
		HSSFCell cell;
		cell = row.createCell(0);
		cell.setCellValue("用户编号");
		cell.setCellStyle(style);
		
		cell = row.createCell(1);
		cell.setCellValue("用户名称");
		cell.setCellStyle(style);
		
		cell = row.createCell(2);
		cell.setCellValue("用户密码");
		cell.setCellStyle(style);
		
		cell = row.createCell(3);
		cell.setCellValue("登录时间");
		cell.setCellStyle(style);
	}
	
	/**
	 * 实现下载Excel表格功能接口
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	private void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		/*告诉浏览器用什么软件打开此应用*/
		response.setHeader("content-Type", "application/msexcel");
		/*下载文件的默认名称*/
		response.setHeader("Content-Disposition", "attachment;filename=userdata.excel");
		Document document = new Document();
		document.open();
		
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("用户统计表");
		createTableTitle(workbook, sheet);
		List<User> list = userService.findAllUser();
		
		//设置日期格式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
		
		//新增数据行，并且设置单元格数据
		int rowNum = 1;
		
		for (User user : list) {
			HSSFRow row = sheet.createRow(rowNum);
			row.createCell(0).setCellValue(String.valueOf(user.getUserId()));
			row.createCell(1).setCellValue(user.getUserName().toString());
			row.createCell(2).setCellValue(user.getUserPassword().toString());
			/*row.createCell(3).setCellValue(user.getUserLoginTime().toString());*/
			
			HSSFCell cell = row.createCell(3);
			cell.setCellValue(user.getUserLoginTime());
			cell.setCellStyle(style);
			rowNum++;
		}
		
		
		
		//拼装blobName
		String fileName	= "测试数据统计表.xlsx";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String dateTime = dateFormat.format(new Date());
		String blobName = dateTime+"/"+UUID.randomUUID().toString().replaceAll("-", "")+"/"+fileName;
		
		//获取或创建爱Container
		ByteArrayOutputStream out = new ByteArrayOutputStream();  
		workbook.write(out);  
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());  
		/*CloudBlobContainer blobContainer = BlobHandler.get*/
        response.getOutputStream();
        document.close();
	}
}
