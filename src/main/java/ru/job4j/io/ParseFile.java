package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {

    //- Избавиться от get set за счет передачи File в конструктор.
    //- Ошибки в многопоточности. Сделать класс Immutable. Все поля final.
    //- Ошибки в IO. Не закрытые ресурсы. Чтение и запись файла без буфера.
    //- Нарушен принцип единой ответственности. Тут нужно сделать два класса.
    //- Методы getContent написаны в стиле копипаста. Нужно применить шаблон стратегия. content(Predicate<Character> filter)

    public final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    private synchronized String getContent(Predicate<Character> predicate) throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) > 0) {
                if (predicate.test((char) data)) {
                    output.append((char) data);
                }
            }
        }
        return output.toString();
    }

}
