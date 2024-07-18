import java.io.File;
import java.util.*;

public class Main {

    private static Kart[][] kartlar = new Kart[4][4];


    public static char getRandomChar(ArrayList<Character> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        char selectedChar = list.get(index);
        list.remove(index);
        return selectedChar;
    }


    public static void oyunTahtası(){

        for(int i = 0;i < 4;i++){
            System.out.println("-------------------");
            for(int j = 0;j < 4;j++){

                if(kartlar[i][j].isTahmin()){
                    System.out.print(" |" + kartlar[i][j].getDeger() + "| ");
                }
                else {
                    System.out.print(" | | ");
                }

            }
            System.out.println("");

        }
        System.out.println("-------------------");

    }



    public static boolean oyunbittimi(){
        for(int i = 0;i < 4;i++){

            for(int j = 0;j < 4;j++){

                if(kartlar[i][j].isTahmin() == false ){
                    return false;
                }
            }

        }
        return true;
    }



    public static void tahminEt() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Birinci tahmini giriniz(i ve j değerlerini bir boşluklu girin) : ");
        int i1 = scanner.nextInt();
        int j1 = scanner.nextInt();



        System.out.print("İkinci tahmini giriniz(i ve j değerlerini bir boşluklu girin) : ");
        int i2 = scanner.nextInt();
        int j2 = scanner.nextInt();

        if(kartlar[i1][j1].isTahmin()==true || kartlar[i2][j2].isTahmin()==true){
            System.out.println("Bu değer veya değerler daha öneden açılmış başka değer girin");
        }

        else {

            if( (i1 == i2) && (j1 == j2)){
                System.out.println("Aynı yerler girilemez");
            }
            else if(kartlar[i1][j1].getDeger() == kartlar[i2][j2].getDeger()){

                kartlar[i1][j1].setTahmin(true);
                kartlar[i2][j2].setTahmin(true);
                oyunTahtası();
            }
            else {
                kartlar[i1][j1].setTahmin(true);
                kartlar[i2][j2].setTahmin(true);
                oyunTahtası();
                Thread.sleep(3000);
                kartlar[i1][j1].setTahmin(false);
                kartlar[i2][j2].setTahmin(false);
                oyunTahtası();


            }
        }


    }



    public static void kayıttanAl(){

        Scanner scanner = new Scanner(System.in);
        File file = new File("kayıt.bin");

        while (true){
            if(file.exists()){
                System.out.print("Kaydedilmiş bir oyununuz var kayıttan devam etmek istermisiniz(y,n) : ");
                String cevap = scanner.nextLine();

                if(cevap.equals("y")){
                    kartlar = OyunKayıt.kayıttanAl();
                    return;
                }
                else if(cevap.equals("n")){
                    break;
                }
                else {
                    System.out.println("geçersiz bir komut");
                    continue;
                }
        }


        }

        ArrayList<Character> charList = new ArrayList<>();
        for (char ch = 'A'; ch <= 'H'; ch++) {
            charList.add(ch);
            charList.add(ch);
        }

        // ArrayList'i karıştır
        Collections.shuffle(charList);


        for(int i = 0;i < 4;i++){

            for (int j = 0;j < 4;j++){

                kartlar[i][j] = new Kart(getRandomChar(charList));

            }

        }

    }





    public static void main(String[] args) throws InterruptedException {


        Scanner scanner = new Scanner(System.in);

        kayıttanAl();
        oyunTahtası();



        while(oyunbittimi() == false){

            System.out.print("Çıkış için q ya basın istemiyorsanız herhangi bir tuşa basın : ");
            String cıkıs = scanner.nextLine();

            if(cıkıs.equals("q")){
                System.out.print("Oyunu kaydetmek için y kaydetmemek için n yazın : ");
                String kayıt = scanner.nextLine();

                if(kayıt.equals("y")){
                    OyunKayıt.oyunkaydet(kartlar);
                    System.out.println("Programdan çıkılıyor");
                    break;
                }
                else if(kayıt.equals("n")){
                    System.out.println("Oyun kaydedilmedi");
                    System.out.println("Programdan çıkılıyor");
                    break;
                }
                else {
                    System.out.println("Geçersiz komut");
                    continue;
                }

            }

            tahminEt();
        }



    }



}


