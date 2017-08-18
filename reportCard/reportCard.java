package com.example.android.reportcard;

public class reportCard {
    private String StudentName;
    private String subjectName;
    private String Classs;
    private  int marks;
    private String Grade;

    public reportCard(String Name,String Subject,String c,int mark, String grades) {
        StudentName= Name;
        subjectName = Subject;
        Classs= c;
        marks= mark;
        Grade = grades;
    }
    public String getStudentName() { return StudentName; }
    public void setStudentName(String name) {
        StudentName = name;
    }

    public String getSubjectName() {
        return subjectName;
    }
    public String getClasss() { return Classs; }
    public void setclasss(String c) {
        Classs = c;
    }

    public void setSubjectName(String Subject) {
        subjectName = Subject;
    }

    public int getMarks() { return marks; }
    public void setMarks(int m) {
        marks =m;
    }

    public String getGrade() {
        return Grade;
    }
    public void setGrade(String grades) {
        Grade = grades;
    }

    @Override
    public String toString() {
        return StudentName + " " +subjectName+" "+Classs+" "+marks+" "+ Grade;
    }
}
