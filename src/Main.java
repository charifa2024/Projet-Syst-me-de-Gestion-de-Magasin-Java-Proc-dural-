import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Ajout de quelques produits pour tester
        Produit.produits[0] = new Produit("Laptop", 4500, 10);
        Produit.produits[1] = new Produit("Souris", 150, 3); // Pour tester l'alerte
        Produit.produits[2] = new Produit("Clavier", 250, 8);
        Produit.nbProduits = 3;
        Produit.count = 3;
        Produit produit = new Produit();
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
                case 6: {
                    /// sauvegarder les données
                break;
                }
                case 7:
                    boucle=false;
                    System.out.println("vous avez quitter le système !!");
                    break;

                default:
                    System.out.println("Option invalide !");

            }
            System.out.println();
        }while (boucle);

        sc.close();

    }
}