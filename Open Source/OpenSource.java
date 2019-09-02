import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class OpenSource {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner kb = new Scanner(new File("open.dat"));
        ArrayList<Project> projects = new ArrayList<>();
        HashMap<String, Project> students = new HashMap<>();

        while(true){
            String next = kb.nextLine();
            if(next.equals("1") && kb.next().equals("0")) {
                break;
            }
            if(Character.isUpperCase(next.charAt(0))) {
                projects.add(new Project(next));
            } else if (Character.isLowerCase(next.charAt(0))) {
                if(students.containsKey(next) && !projects.get(projects.size()-1).studentsInProject.contains(next)) {
                    students.get(next).studentsInProject.remove(next);
                    students.remove(next);
                } else {
                    students.put(next,projects.get(projects.size()-1));
                    projects.get(projects.size()-1).studentsInProject.add(next);
                }
            }
        }
        Collections.sort(projects);
        for(Project p : projects) {
            System.out.println(p.name + " " + p.studentsInProject.size());
        }
    }
}

class Project implements Comparable<Project> {
    public HashSet studentsInProject;
    String name;
    public Project(String s) {
        studentsInProject = new HashSet();
        this.name = s;
    }
    public int compareTo(Project otherProject)
    {
        int memberDiff = otherProject.studentsInProject.size() - this.studentsInProject.size();
        if(memberDiff != 0)
            return memberDiff;
        return this.name.compareTo(otherProject.name);
    }
}