package testpackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataTest {
    private static String questionary_path = "questionary.csv";
    private static String separator = ";";
    private static File questionary = new File(questionary_path);

    public static void registerQuestion(Question question) {
        try {
            if (!questionary.exists()) {
                questionary.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(questionary, true);
            fileWriter.write(question.getStatement() + separator + question.getOption1() + separator 
                    + question.getOption2() + separator + question.getOption3() + separator 
                    + question.getCorrectOption() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error al guardar la pregunta: " + e.getMessage());
        }
    }

    public static ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questions_list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(questionary_path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(separator);
                String statement = fields[0];
                String option1 = fields[1];
                String option2 = fields[2];
                String option3 = fields[3];
                int correctOption = Integer.parseInt(fields[4]);
                
                Question q = new Question(statement, option1, option2, option3, correctOption);
                questions_list.add(q);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions_list;
    }
}
