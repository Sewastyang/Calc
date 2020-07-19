import java.util.Scanner;

public class Calc {
    public Line input; // элемент класса входной строки пльзователя
    private boolean Rom; // 1 если Римские
    private boolean Arab; // 1 если Арабские

    public String[] RomiDec= {"X","XX","XXX","XL","L","LX","LXX","LXXX","XC","C"}; //Римские десятки
    public String[] ArabiDec= {"10","20","30","40","50","60","70","80","90","100"}; // Арабские десятки
    public String[] Romi= {"I","II","III","IV","V","VI","VII","VIII","IX","X"}; // Римские числа от 1 до 10
    public String[] Arabi={"1","2","3","4","5","6","7","8","9","10"};           // Арабские числа от 1 до 10

    public int result;      // Результат калькулирования
    public String res="rege ";      // Результат калькулирования для Римских

    public Calc(Line input){
        this.input=input;
        Rom=false;
        Arab=false;

    }
    public void FunctionOf() throws Exception { // Функция которая определяет, Римские или Арабские введенные числа
        boolean lR=false;   // Левый Римский операнд
        boolean rR=false;   // Правый Римский операнд
        boolean lA=false;   // Левый Арабский операнд
        boolean rA=false;   // Правый Арабский операнд

        for(int i=0;i<10;i++)   // Цикл для присвоения значения булевым переменным операндов
        {
            if (input.left.equals(Romi[i])) {
                lR=true;
            }
            if(input.right.equals(Romi[i])){
                rR=true;
            }
        }
        if(lR && rR)    // Если правый и левый операнд Римские, то выражение с Римскими
            this.Rom=true;


        for(int i=0;i<10;i++) {     // Цикл для присвоения значения булевым переменным операндов
            if (input.left.equals(Arabi[i])) {
                lA=true;
            }
            if(input.right.equals(Arabi[i])){
                rA=true;

            }
        }
        if(lA && rA)   // Если правый и левый операнд Арабские, то выражение с Арабскими
            this.Arab=true;

        if (!this.Arab && !this.Rom){
           throw new Exception("Операнды могут принимать целые значения от 1 до 10 и \n" +
                   "Быть или Арабскими, или Римскими!!! \n" +
                   "Попробуйте еще раз =)");

            }


        return;

    }

    public void Calculate() {   // Функция в которой происходит вычисление
        if (this.Arab) {    // Для Арабских цифр
            switch (input.operators.get(input.indexOfOperation)) {
                case "+":
                    result = Integer.parseInt(input.left) + Integer.parseInt(input.right);
                    break;
                case "-":
                    result = Integer.parseInt(input.left) - Integer.parseInt(input.right);
                    break;
                case "*":
                    result = Integer.parseInt(input.left) * Integer.parseInt(input.right);
                    break;
                case "/":
                    result = Integer.parseInt(input.left) / Integer.parseInt(input.right);
                    break;

            }
        }

        if (this.Rom) {     // Для Римских цифр
            for (int i=0;i<10;i++){         // Ищем из списка Римских цифр наши операнды, и присваиваем операндам значения из Арабских
                if(this.Romi[i].equals(input.left)){
                    input.left=this.Arabi[i];
                }
                if(this.Romi[i].equals(input.right)){
                    input.right=this.Arabi[i];
                }
            }
            switch (input.operators.get(input.indexOfOperation)) {
                case "+":
                    result = Integer.parseInt(input.left) + Integer.parseInt(input.right);
                    break;
                case "-":
                    result = Integer.parseInt(input.left) - Integer.parseInt(input.right);
                    break;
                case "*":
                    result = Integer.parseInt(input.left) * Integer.parseInt(input.right);
                    break;
                case "/":
                    result = Integer.parseInt(input.left) / Integer.parseInt(input.right);
                    break;

            }
            this.ToRes(); // Вызов функции, для перевода результата из Арабских в Римские
                          // Так как все вычисления производятся в Арабских


        }
        return;
    }
    public void ToRes(){ // Функция перевода результата из Арабских в Римские
        int dec, ed; // Арабские значения десятков и единиц
        String decc = null, edd= null; // Римские значения десятков и единиц
        dec= this.result/10*10; // Вычисляем десятки (избавляемся от единиц)
        ed= this.result %10;    // Вычисляем единицы (через остаток от целочисленного деления на 10)

        for(int i=0;i<10;i++){  // Цикл для поиска соответствующих значений из массивов десятков и единиц
            if(dec== Integer.parseInt(this.ArabiDec[i])){   // Так как индексы соответствующих чисел из разных систем
                decc=this.RomiDec[i];                       // идентичны для Римских и Арабских
            }
            if(ed== Integer.parseInt(this.Arabi[i])){
                edd=this.Romi[i];
            }
        }

        if(decc!=null) {              // Выбираем как выводить результат, если десятки или единицы имеют значение null
            if(edd!=null)
            this.res = decc + edd;
            else this.res=decc;
        }
            else this.res = edd;

        if(this.result==100)        // Исключение для 100
            this.res=decc;

    }
    public static void main(String[] args) {

        try {                                                   // Отлов исключений
            Scanner in=new Scanner(System.in);                  // Инициализируем сканер для отлова ввода
            System.out.printf("Введите операцию: \n");          // Выводим приветственное сообщение
            Line input = new Line(in.nextLine());               // Читает ввод из консоли в новый класс типа Лаин
            input.Identification();                             // Подготавливаем операнды и операцию
            Calc c=new Calc(input);                             // Инициализируем класс калькулятора
            c.FunctionOf();                                     // Вызываем функцию для понимания с чем работаем
            c.Calculate();                                      // Считаем
            if(c.Arab)                                          // Выбираем формат результата
                System.out.printf("Результат: %s ", c.result);

            if(c.Rom)
                System.out.printf("Результат: %s ", c.res);

        } catch (Exception e){                                   // Выкидываем исключение
            System.out.println(e.getMessage());

        }
    }
}

