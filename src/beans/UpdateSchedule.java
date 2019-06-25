package beans;

import java.util.Map;

public class UpdateSchedule
{
    private String studentID;
    private String[] scheduleString = {"chooseTile", "taskPlan", "midtermReport", "finalReport"};

    public UpdateSchedule(String studentID)
    {
        this.studentID = studentID;
    }

    public void toNextSchedule()
    {
        DBUtil db = new DBUtil();
        String query = "select schedule from studentInfo where ID=" + studentID;
        String update = "update studentInfo set schedule=? where ID=" + studentID;
        Map queryResult = db.getMap(query, new String[]{});
        String now = (String) queryResult.get("schedule");
        for (int i = 0; i < scheduleString.length; i++)
        {
            if (scheduleString[i].equals(now))
            {
                db.update(update,new String[]{scheduleString[i+1]});
                break;
            }
        }
    }
}
