package com.youda.fileos;

import com.youda.dao.statistics.IncomeMapper;
import com.youda.request.statistics.StatisticsRequest;
import com.youda.response.statistics.IncomeResponse;
import com.youda.service.statistics.IncomeService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @CreateTime:2018/3/4 16:56
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 导出Excel文档类
 */

@Controller
@RequestMapping(value = "/output")
public class OutputExcelController {

    @Autowired
    private IncomeMapper incomeMapper;

    @PostMapping(value = "/excel")
    public void outputExcel(HttpServletRequest request, HttpServletResponse response, @RequestBody StatisticsRequest statisticsRequest) throws IOException {

        //声明导出excel表头字段 ，我这是根据前端传来的起始时间来查询数据库里的数据，如果没有输入变量要求，保留前两个就行
        String[] headers = {"ID","主题","总收入","总付款人数","开始时间","结束时间"};

        //查询出来的数据
        List<IncomeResponse> data = (List<IncomeResponse>) incomeMapper.customTime(statisticsRequest);

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //声明一个表格
        HSSFSheet sheet = workbook.createSheet();
        //设置默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 18);
        HSSFRow row = sheet.createRow(0);
        for (short i=0; i<headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //遍历集合数据，产生数据行
        Iterator<IncomeResponse> it = data.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            IncomeResponse ir = (IncomeResponse) it.next();
            //利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields = ir.getClass().getDeclaredFields();
            for (short i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i);
                Field field = fields[i];
                String fieldName = field.getName();
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                try {
                    Class tCls = ir.getClass();
                    Method getMethod = tCls.getMethod(getMethodName,
                            new Class[]{});
                    Object value = getMethod.invoke(ir, new Object[]{});
                    String textValue = null;


                    if (value instanceof Date)
                    {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        textValue = sdf.format(date);
                    }
                    else
                    {
                        //其它数据类型都当作字符串简单处理
                        textValue = value.toString();
                    }

                    HSSFRichTextString richString = new HSSFRichTextString(textValue);
                    HSSFFont font3 = workbook.createFont();
                    font3.setColor(HSSFColor.BLUE.index);//定义Excel数据颜色
                    richString.applyFont(font3);
                    cell.setCellValue(richString);

                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=createList.xls");//默认Excel名称
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
    /*public static void main(String[] args) {
        File file = new File("HelloWorld.java");
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        System.out.println(suffix);
    }*/
}
