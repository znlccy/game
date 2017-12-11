package com.youda.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.youda.model.User;
import com.youda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-30
 * @introduce 定义导出pdf文件的控制器
 */

@RestController
@RequestMapping(value = "/pdf")
@CrossOrigin(maxAge = 3600,origins = "*")
public class ExportPDFController {
	
	/**
	 * 实现用户服务的自动依赖注入
	 */
	@Autowired
	private UserService userService;

	/**
	 * 实现下载PDF文件
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/download" ,method = RequestMethod.GET)
	private void downloadPDF(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		/*告诉浏览器用什么软件打开此应用*/
		response.setHeader("content-Type", "application/pdf");
		/*下载文件的默认名称*/
		response.setHeader("Content-Disposition", "attachment;filename=userdata.pdf");
		
		Document document = new Document();
		/*实现写入PDF文件*/
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		/*查找所有用户信息*/
		List<User> list = userService.findAllUser();
		for (User user : list) {
			//实现pdf表格样式，以及设置表格几行几列
			PdfPTable table = new PdfPTable(3);
			PdfPCell cell = new PdfPCell();
			
			cell.setPhrase(new Paragraph(String.valueOf(user.getUserId())));
			table.addCell(cell);
			document.add(table);
			
			cell = new PdfPCell();
			cell.setPhrase(new Paragraph(user.getUserName().toString()));
			table.addCell(cell);
			document.add(table);
			
			cell = new PdfPCell();
			cell.setPhrase(new Paragraph(user.getUserPassword().toString()));
			table.addCell(cell);
			document.add(table);
			
		}
		document.close();
	}
	
}
