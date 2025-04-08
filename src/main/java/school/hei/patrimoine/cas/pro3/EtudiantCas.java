package school.hei.patrimoine.cas.pro3;

import static java.time.Month.APRIL;
import static java.time.Month.MAY;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Argent.euro;
import static school.hei.patrimoine.modele.Devise.MGA;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Argent;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.*;

public class EtudiantCas extends Cas {
  public EtudiantCas(LocalDate ajd, LocalDate finSimulation, Map<Personne, Double> possesseurs) {
    super(ajd, finSimulation, possesseurs);
  }

  @Override
  protected Devise devise() {
    return MGA;
  }

  @Override
  protected String nom() {
    return "Cas de l'étudiant PRO3";
  }

  @Override
  protected void init() {}

  @Override
  protected void suivi() {}

  @Override
  public Set<Possession> possessions() {
    var BMOI = new Compte("BMOI", LocalDate.of(2025, APRIL,8 ), new Argent(625_000 , MGA));
    var BNI = new Compte("BMI", LocalDate.of(2025, APRIL, 8),new Argent(2_000_000, MGA));
    var CoffreFort = new Compte("Coffre", LocalDate.of(2025, APRIL, 8),new Argent(1_750_000, MGA));
    var debutEpargne = LocalDate.of(2025, MAY, 2);
    var Colocation = LocalDate.of(2025, APRIL, 26);
    var depense = LocalDate.of(2025, MAY, 1);
    var Acquisition = LocalDate.of(2025, APRIL,8 );
    new FluxArgent("Contrat", BNI, debutEpargne, debutEpargne.plusYears(1), 2, ariary(2_125_000));
    new FluxArgent("colocation", BNI , Colocation,Colocation.plusYears(1), 26, ariary(-600_000));
    new FluxArgent("depense", BNI , depense,depense.plusYears(1), 1, ariary(-700_000));
    var Materiel = new Materiel("ordinateur", Acquisition, Acquisition.plusYears(1), ariary(3_000_000),0.12);
    new TransfertArgent("virement vers BMOI", BNI, BMOI,LocalDate.of(2025, MAY, 3 ),ariary(200_000));
    return Set.of(BMOI, BNI, CoffreFort, Materiel);
  }
}
