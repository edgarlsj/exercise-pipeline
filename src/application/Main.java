package application;

import entities.Product;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

//        System.out.println("Enter full file path:");
        String patch = "C:\\temp\\in-pipeline.txt";//le o caminho do arquivo

        try (BufferedReader reader = new BufferedReader(new FileReader(patch))) {//cria um buffer para ler o arquivo

            List<Product> list = new ArrayList<>();//cria uma lista de produtos
            String line = reader.readLine();//le a primeira linha do arquivo

            while (line != null) {//enquanto a linha for diferente de nulo
                String[] fields = line.split(",");//separa os campos da linha armazenando em um array
                list.add(new Product(fields[0], Double.parseDouble(fields[1])));//adiciona um produto na lista com os campos separados
                line = reader.readLine();//le a proxima linha

            }
            Double avg = list.stream()
                    .map(p -> p.getPrice())
                    .reduce(0.0, (x, y) -> x + y) / list.size();

            System.out.printf("Average price:  "+ String.format("%.2f",avg));

            Comparator <String> comp = (s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());



            List<String> names = list.stream()
                    .filter(p -> p.getPrice() < avg)
                    .map(p -> p.getName())
                    .sorted(comp.reversed())
                    .collect(Collectors.toList());

            names.forEach(System.out::println);



        }catch (IOException e){
            e.printStackTrace();

        }

    }
}
