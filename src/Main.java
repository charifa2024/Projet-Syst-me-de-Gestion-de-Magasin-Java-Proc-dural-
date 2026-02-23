import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Produit produit = new Produit();
        produit.chargerStock();
        Scanner sc = new Scanner(System.in);

        System.out.println("Projet : Système de Gestion de Magasin");
        boolean boucle = true;
        do {


            int option = produit.saisirEntier();

            switch (option) {
                case 1:
                    produit.ajouterProduit();
                    break;
                case 2:
                    produit.afficherStock();
                    break;
                case 3:
                    System.out.println("Veuillez entrer le nom de produit à rechercher : ");
                    String nom = sc.nextLine();
                    int indexProd = produit.rechercherProduit(nom);
                    if(indexProd != -1) {
                        produit.afficherInfoProduit(indexProd);
                    }else {
                        System.out.println("produit non existe !");
                    }
                    break;

                case 4:
                    produit.effectuerVente();
                    break;
                case 5:
                    produit.etatAlerte();
                    break;
                case 6:
                    produit.sauvegarderStock();
                    break;
                case 7:
                    boucle=false;
                    System.out.println("vous avez quitter le système !!");
                    break;

                default:
                    System.out.println("Veuillez d'entier un choix de 1-7 !");

            }
            System.out.println();
        }while (boucle);

        sc.close();

    }
}