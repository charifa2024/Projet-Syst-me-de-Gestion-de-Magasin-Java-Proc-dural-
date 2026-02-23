import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
public class Produit {
    static int count = 0;
    int idProduit ;// auto increment & unique
    String nomProduit ;
    double prixProduit ;
    int quantiteStock ;
    static int nbProduits = 0;
    static public Produit[] produits = new Produit[10];



    public void afficherInfoProduit(int indexProd){
        System.out.println("Informations Produit : ");
        System.out.println("Nom du produit : "+produits[indexProd].nomProduit);
        System.out.println("Le prix unitaire : "+produits[indexProd].prixProduit+"dh");
        System.out.println("La quantité dans le stock : "+produits[indexProd].quantiteStock);

    }

    public Produit(){
    }

    public Produit(String nomProduit , double prixProduit , int quantiteInitiale  ){
        this.idProduit = count;
        count++;nbProduits++;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit ;
        this.quantiteStock = quantiteInitiale;
    }

    static String[] menu = {
            "Ajouter un nouveau produit",
            "Afficher l'inventaire complet",
            "Rechercher un produit (par nom)",
            "Réaliser une vente (avec mise à jour stock)",
            "Afficher les alertes (stock bas < 5)",
            "Sauvegarder les données",
            "Quitter"
    };



     public void afficherMenu(){
        System.out.println("Voici le Menu : ");

        for (int i=0 ; i<7 ; i++){
            System.out.println((i+1)+"_"+menu[i]);
        }


    }


    public int saisirEntier(){
        afficherMenu();
        int option = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Veuillez de choisir un nombre(1-7)!! :");
            option = sc.nextInt();
        } while (option < 1 || option > 7);
        return option;
    }
    public void ajouterProduit() {
        Scanner sc = new Scanner(System.in);

        if (nbProduits >= produits.length) {
            System.out.println("Le tableau des produits est plein !!");
        } else {
            System.out.println("Veuillez d'entrer le nom du produit : ");
            String nom = sc.nextLine();
            System.out.println("Veuillez entrer le prix :");
            double prix = sc.nextDouble();
            System.out.println("Veuillez d'entrer la quantité initiale : ");
            int quantity = sc.nextInt();


            produits[nbProduits] = new Produit(nom, prix, quantity);
            nbProduits++;
            count++;
            System.out.println("Le produit est ajouté !");
        }

    }

    public void modifierPrix(){
         Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez d'entrer le nom de produit à modifier : ");
        String nom = sc.nextLine();
        int index = rechercherProduit(nom);
        if (index == -1){
            System.out.println("Le produit n'existe pas !!");

        }else {

            System.out.println("Veuillez d'entrer le nouveau prix : ");
            double prix = sc.nextDouble();
            produits[index].prixProduit = prix;
            System.out.println("Le prix a était modifié !");
        }

    }
    public int rechercherProduit(String nom){
         int indexProd = -1;
         for (int i=0 ; i<produits.length ; i++){
             if (produits[i]!= null && produits[i].nomProduit.equals(nom)){
                 indexProd = i;
             }

         }
        return indexProd;
    }
    public void effectuerVente(){
         Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez d'entrer le nom du produits : ");
        String nom = sc.nextLine();

        System.out.println("Veuillez d'entrer la qantité souhaité : ");
        int quantity = sc.nextInt();

        int indexProd = rechercherProduit(nom);
        if(indexProd == -1){
            System.out.println("Le produit n'existe pas !!");
        }else if(produits[indexProd].quantiteStock < quantity) {
            System.out.println("La quantité est insuffisante !!");
        }else {
            double total = quantity * produits[indexProd].prixProduit;
            if(total > 1000){
                total -= (total*10)/100 ;
                produits[indexProd].quantiteStock -= quantity;
                afficherTicket(nom,quantity,produits[indexProd].prixProduit,total);
            }
        }

    }

    void afficherTicket(String nom, int quantity, double prixProduit, double total) {
        System.out.println("Ticket de caisse :");
        System.out.println("Le nom du produit : "+nom);
        System.out.println("La quantité : "+quantity);
        System.out.println("Le prix unitaire : "+prixProduit);
        System.out.println("Le Total : "+total+" dh");
    }

    public void afficherStock(){
        System.out.println("Voici les produits trouvés dans le stock :");

         for(Produit prod : produits) {
             if (prod != null) {
                 System.out.println("Nom : " + prod.nomProduit + "\n" + "Prix :" + prod.prixProduit + "dh \n" + "QTE : " + prod.quantiteStock + "\n" + "Valeur Totale : " + (prod.quantiteStock * prod.prixProduit) + "dh\n");
             }
         }
    }
    public void etatAlerte(){
        System.out.println("Attention !! Produit a quantité presque nulle !! ");
         for (Produit prod : produits){
             if(prod!=null) {
                 if (prod.quantiteStock <= 5) {
                     System.out.println("Produit : " + prod.nomProduit);
                     System.out.println("Quantité : " + prod.quantiteStock + "\n");
                 }
             }
         }
    }

    public void sauvegarderStock() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("stock.txt"));

            for (int i = 0; i < nbProduits; i++) {
                if (produits[i] != null) {
                    writer.println(
                            produits[i].nomProduit + ";" +
                                    produits[i].prixProduit + ";" +
                                    produits[i].quantiteStock
                    );
                }
            }

            writer.close();
            System.out.println("Données sauvegardées !");
        } catch (IOException e) {
            System.out.println("Erreur données non sauvegardées !");
        }
    }

    void chargerStock() {
        try {
            File file = new File("stock.txt");

            if (!file.exists()) {
                System.out.println("Aucun fichier trouvé!!");
                return;
            }

            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String ligne = reader.nextLine();
                String[] parts = ligne.split(";");

                String nom = parts[0];
                double prix = Double.parseDouble(parts[1]);
                int quantite = Integer.parseInt(parts[2]);

                produits[nbProduits] = new Produit(nom, prix, quantite);
                nbProduits++;
            }

            reader.close();
            System.out.println("Stock chargé !");
        } catch (FileNotFoundException e) {
            System.out.println("Erreur stck non chargé !");
        }
    }



}
