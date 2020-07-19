import java.util.ArrayList;

public class Line {

    public String left;   // Левый операнд
    public String right;  // Правый операнд
    public ArrayList <String> operators= new ArrayList<String>(); // Возможные операции
    public String input; // Входная строка пользователя
    public int indexOfOperation; // номер операции пользователя из листа операций

    public Line(String input){
        this.operators.add("/");
        this.operators.add("+");
        this.operators.add("-");
        this.operators.add("*");


        this.input=input;
    }

    public void Identification() throws Exception {

            for (int j=0;j<operators.size();j++){   // Узнаю номер операции из списка операций
                if(this.input.contains(operators.get(j))){
                    this.indexOfOperation=j;
                }
            }


            String operation=operators.get(indexOfOperation); // Строка с операцией для обработки исключений
            if(operation=="+")          // Исключение для сложения
                operation="\\+";
            if(operation=="*")          // Исключение для умножения
                operation="\\*";

            String[] str=input.split(operation); // делю строку входную на правую и левую часть
            left = str[0].trim();                                             // Левый операнд = левой части входной строки, без пробелов

            right = str[1].trim();                                            // Правый операнд = правой части входной строки, без пробелов

    }


}
