package ltd.scau.utils.jwc;

import com.opensymphony.xwork2.ActionContext;
import ltd.scau.entity.Exam;
import ltd.scau.entity.Grade;
import ltd.scau.entity.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class JwcParser {


    public static String parseName(String html) {
        Document doc = Jsoup.parse(html);
        Element name = doc.getElementById("xhxm");
        if (name == null || !name.text().contains("同学"))
            return "";
        return name.text().substring(0, name.text().length() - 2);
    }

    public static String parseError(String html) {
        Document doc = Jsoup.parse(html);
        Element error = doc.getElementById("content_no");
        return error == null ? "" : error.text();
    }

    public static List<Grade> parseGrade(String html) {
        Document doc = Jsoup.parse(html);
        Elements grade = null;
        try {
            grade = doc.getElementById("Datagrid1").getElementsByTag("tr");
        } catch (NullPointerException ex) {
            return null;
        }

        if (grade.size() - 1 <= 0) return null;
        List<Grade> grades = new ArrayList<>();
        String[][] table = new String[grade.size() - 1][19];
        // 0学年 1学期 2课程代码 3课程名称 4课程性质 5课程归属 6学分 7绩点 8平时成绩 9期中成绩 10期末成绩 11实验成绩
        // 12成绩 13辅修标记 14补考成绩 15重修成绩
        // 16开课学院 17备注 18重修标记

        // 0year 1semester 2course code 3course title 4course property 5course
        // attribution 6credit 7GPA 8usual 9mid-term 10final 11experiment 12total
        // 13minor 14resit 15retake 16college 17remarks 18retake mark

        for (int i = 1; i < grade.size(); i++) {
            Elements line = grade.get(i).getAllElements();
            for (int j = 1; j < 20; j++) {
                String text = line.get(j).text();
                table[i - 1][j - 1] = pure(text);
                // System.err.printf("%s%s", line.get(j).text(), j == 19 ? "\n"
                // : "\t");
            }
        }
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
        for (int i = 0; i < table.length; i++) {
            Grade g = new Grade();
            g.setYear(pure(table[i][0]));
            g.setSemester(pure(table[i][1]));
            g.setCode(pure(table[i][2]));
            g.setTitle(pure(table[i][3]));
            g.setProperty(pure(table[i][4]));
            g.setAttribution(pure(table[i][5]));
            g.setCredit(parseDouble(pure(table[i][6])));
            g.setGpa(parseDouble(pure(table[i][7])));
            g.setUsual(parseDouble(pure(table[i][8])));
            g.setMidTerm(parseDouble(pure(table[i][9])));
            g.setFinalExam(parseDouble(pure(table[i][10])));
            g.setExperiment(parseDouble(pure(table[i][11])));
            g.setTotal(parseDouble(pure(table[i][12])));
            g.setMinor(pure(table[i][13]));
            g.setResit(parseDouble(pure(table[i][14])));
            g.setRetake(parseDouble(pure(table[i][15])));
            g.setCollege(pure(table[i][16]));
            g.setRemarks(pure(table[i][17]));
            g.setMarkRetake(pure(table[i][18]));
            g.setStuId(user.getStuId());
            grades.add(g);
        }
        return grades;
    }

    public static String selectCourseTable(String html) {
        Document document = Jsoup.parse(html);
        Element table = document.getElementById("Table1");
        Elements courses = table.getElementsByAttribute("rowspan");
        for (Element e : courses) {
            System.out.println(e.html());
        }
        return courses.toString();
    }

    public static List<Exam> parseExam(String html) {
        Document document = Jsoup.parse(html);
        Element table = document.getElementById("DataGrid1");
        Elements rows = table.getElementsByTag("tr");
        ArrayList<Exam> exams = new ArrayList<>();
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
        for (int i = 1; i < rows.size(); i++) {
            Elements tr = rows.get(i).getAllElements();
            Exam e = new Exam();
            e.setStuId(user.getStuId());
            e.setCode(pure(tr.get(1).text()));
            e.setTitle(pure(tr.get(2).text()));
            e.setStuName(pure(tr.get(3).text()));
            e.setTime(pure(tr.get(4).text()));
            e.setLocale(pure(tr.get(5).text()));
            e.setForm(pure(tr.get(6).text()));
            e.setSeat(pure(tr.get(7).text()));
            e.setZone(pure(tr.get(8).text()));
            exams.add(e);
        }
        return exams;
    }

    public static String pure(String str) {
        return str.equals(new String(new byte[]{-62, -96})) ? "" : str;
    }

    public static Double parseDouble(String str) {
        return str.equals("") ? 0.0 : Double.parseDouble(str);
    }

}
