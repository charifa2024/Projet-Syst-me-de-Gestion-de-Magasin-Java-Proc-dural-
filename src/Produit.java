import java.util.Scanner;

public class Produit {
    static int count = 0;
    int idProduit ;// auto increment & unique
    String nomProduit ;
    double prixProduit ;
    int quantiteStock ;
    static int nbProduits = 0;
    static Produit[] produits = new Produit[10];

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



     static public void afficherMenu(){
        System.out.println("Voici le Menu : ");

        for (int i=0 ; i<7 ; i++){
            System.out.println((i+1)+"_"+menu[i]);
        }

         System.out.println(produits.length);

    }


    static public int saisirEntier(){
        afficherMenu();
        int option = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Veuillez de choisir un nombre(1-7)!! :");
            option = sc.nextInt();
        }while (option<1 ||option>7);
        return option;
    }
    static public void ajouterProduit(){
         Scanner sc = new Scanner(System.in);

         if(count >= produits.length){
             System.out.println("Le tableau des produits est plein !!");
         }else {
             System.out.println("Veuillez d'entrer le nom du produit : ");
             String nom = sc.nextLine();
             System.out.println("Veuillez entrer le prix :");

             double prix = sc.nextDouble();
             System.out.println("Veuillez d'entrer la quantité initiale : ");
             int quantity = sc.nextInt();

             new Produit(nomProduit = nom , prixProduit = prix , quantiteStock = quantity);
             System.out.println("Le produit est ajouté !");
         }

    }
    static public void modifierProduit(){
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
    static public int rechercherProduit(String nom){
         int indexProd = -1;
         for (int i=0 ; i<produits.length ; i++){
             if (produits[i].nomProduit == nom){
                 indexProd = i;
             }

         }
        return indexProd;
    }
    void effectuerVente(){
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

    private void afficherTicket(String nom, int quantity, double prixProduit, double total) {
        System.out.println("Ticket de caisse :");
        System.out.println("Le nom du produit : "+nom);
        System.out.println("La quantité : "+quantity);
        System.out.println("Le prix unitaire : "+prixProduit);
        System.out.println("Le Total : "+total+" dh");
    }

    void afficherStock(){
         for(Produit prod : produits){
             System.out.println("");
             System.out.println("Nom : "+prod.nomProduit+"\n"+"Prix :"+prod.prixProduit+"\n"+"QTE : "+prod.quantiteStock+"\n"+"Valeur Totale : "+(prod.quantiteStock*prod.prixProduit)+"\n");
         }
    }
    void etatAlerte(){
        System.out.println("Attention !! Produit a quantité presque nulle !! ");
         for (Produit prod : produits){
             if(prod.quantiteStock <= 5){
                 System.out.println("Produit : "+prod.nomProduit);
                 System.out.println("Quantité : "+prod.quantiteStock+"\n");
             }
         }
    }
    void sauvegarderStock(){}
    void chargerStock(){}




}
