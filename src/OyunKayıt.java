import java.io.*;

public class OyunKayıt {

    public static void oyunkaydet(Kart[][] kartlar){

        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("kayıt.bin"))){

            System.out.println("Oyun kaydediliyor");
            outputStream.writeObject(kartlar);


        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    public static Kart[][] kayıttanAl(){

        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("kayıt.bin"))){

            Kart[][] cikti = (Kart[][]) inputStream.readObject();
            return cikti;


        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }



}
