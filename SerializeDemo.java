import java.io.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    int age;

    Employee(String n, int a) {
        this.name = n;
        this.age = a;
    }
}

public class SerializeDemo {
    
    // Method untuk melakukan deserialisasi objek dari file biner
    public static Employee deserialize(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Employee) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error saat deserialisasi: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("John Doe", 30);
        Employee e2 = new Employee("Jane Smith", 25);
        String filename = "emp.ser";

        // Menulis objek ke file biner (Serialization)
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(e1); // Menyimpan objek pertama (bisa dikembangkan ke List jika ingin banyak)
            System.out.println("Serialized successfully.");
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        // Membaca kembali objek menggunakan method deserialize()
        Employee restored = deserialize(filename);
        if (restored != null) {
            System.out.println("Deserialized: " + restored.name + ", " + restored.age);
        }
    }
}