package myapp;

//import java.io.Console;
import java.util.*;

public class cart {

    public static void main(String[] args) {
        
        System.out.println("Welcome to your shopping cart");
        List<String> cart = new LinkedList<String>();
        
        while (true) {
            String input = System.console().readLine().trim().replace(",", "");
            String[] words = input.split(" ");
            int len = words.length;

            if (words.length == 0) continue;
            eventHandler(words, cart);
        }
    }

    public static void eventHandler(String[] words, List<String> cart) {

        String trigger = words[0].toLowerCase().trim();
        
        if (trigger.equals("exit") || trigger.equals("quit")) {
            System.out.println("Application Closed");
            System.exit(0);
        }

        if (trigger.equals("list")) {
            listCart(cart);
            return;
        }

        if (trigger.equals("add")) {
            addToCart(words, cart);
            listCart(cart);
            return;
        }

        if (trigger.equals("delete") || trigger.equals("remove")) {
            deleteFromCart(words, cart);
            listCart(cart);
            return;
        }

        // No one of the key words
        System.out.println("Invalid Trigger");
    }

    public static void listCart(List<String> cart) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty");
        } else {
            for(int i = 0; i < cart.size(); i++) {
                System.out.println((i+1) + ". " + cart.get(i));
            }
        }
    }

    public static void addToCart(String[] words, List<String> cart){
        if (words.length < 2) {
            System.out.println("No items added");
        } else {
            int i = 1;
            while (i < words.length) {
                cart.add(words[i]);
                i++;
            }
        }
    }
    
    public static void deleteFromCart(String[] words, List<String> cart) {
        if (words.length < 2) {
            System.out.println("No items deleted");
            return; // TODO
        }

        List<Integer> indices =  new LinkedList<Integer>();
        int i = 1;

        while (i < words.length) {
            try {
                indices.add(Integer.parseInt(words[i]));
            } catch (Exception e) {
                System.out.println(words[i] + " cannot be deleted"); 
                continue;
            }
            i++;
        }
        indices.sort(Comparator.reverseOrder());
        int last = -1;

        for (int j = 0; j < indices.size(); j++) {
            if(indices.get(j) == last) continue;
            last = indices.get(j);

            if (indices.get(j) <= 0 || indices.get(j) > indices.size()) continue;
            cart.remove(indices.get(j) - 1);
        }
    }
}
