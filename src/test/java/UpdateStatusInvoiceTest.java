
import org.example.controller.FactureController;
import org.example.model.Facture;
import org.example.model.Paiement;
import org.example.services.PaiementService;
import org.example.sessions.Session;
import org.example.util.GeneratePaimentPdf;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class UpdateStatusInvoiceTest {
    private final PaiementService paiementService = new PaiementService();
    private final FactureController factureController = new FactureController();

    public String createPaiment(String idInput, String montantInput) {

        try {
            factureController.getAllFactures().stream().filter(facture ->
                    facture.getIdClient() == Session.getCurrentUser().getId() && facture.getStatus().equals("not payed")
            ).forEach(System.out::println);
            System.out.print("Entrer id de facture: ");

            int idFacture = Integer.parseInt(idInput);

            Facture facture = factureController.getFactureById(idFacture);

            if (facture == null) {
                System.out.println("Facture introuvable.");
                return "";
            }

            System.out.print("Montant payé: ");

            BigDecimal montant = new BigDecimal(montantInput);

            LocalDate date = LocalDate.now();

            Paiement paiement = new Paiement(
                    montant,
                    idFacture,
                    java.sql.Date.valueOf(date),
                    Session.getCurrentUser().getId()
            );

            factureController.updateFacture(facture);
            paiementService.effectuerPaiment(paiement);

            System.out.println("Paiement effectué avec succès.");

            return facture.getStatus();

        } catch (NumberFormatException e) {
            System.out.println("Veuillez entrer des valeurs numériques valides.");
        } catch (Exception e) {
            System.out.println("Erreur lors du paiement: " + e.getMessage());
        }
        return "";
    }
}
