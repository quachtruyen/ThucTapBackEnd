import java.io.*;
import java.util.Properties;

public class Xfile {
    public static void writeFile(Student student) {
        FileOutputStream fout;
        ObjectOutputStream oos;
        try {
            if (!hasObject("Students.txt")) {
                fout = new FileOutputStream("Students.txt");
                oos = new ObjectOutputStream(fout);
            } else {
                fout = new FileOutputStream("Students.txt", true);
                oos = new ObjectOutputStream(fout) {
                    @Override
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
            }

            oos.writeObject(student);
            ThreadOne threadOne = new ThreadOne(student);
            threadOne.start();

            fout.close();
            oos.close();
            System.out.println("Ghi thành công");
        } catch (Exception ex) {
            System.err.println("Ghi thất bại " + ex);
        }
    }

    public static Properties readFile(String file) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(file));

        } catch (Exception ignored) {
            System.err.println("Đọc file thất bại!");
        }
        return prop;
    }

    public static boolean hasObject(String filePath) {
        FileInputStream file;
        boolean check = true;
        try {
            file = new FileInputStream(filePath);
            ObjectInputStream inStream = new ObjectInputStream(file);
            if (inStream.readObject() == null) {
                check = false;
            }
            inStream.close();
        } catch (IOException | ClassNotFoundException e) {
            check = false;
        }
        return check;
    }
}
