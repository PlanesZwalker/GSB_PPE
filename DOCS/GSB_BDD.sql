#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: VisiteurMedical
#------------------------------------------------------------

CREATE TABLE VisiteurMedical(
        idvisiteur Int NOT NULL ,
        nom        Varchar (255) ,
        motdepasse Varchar (255) ,
        prenom     Varchar (25) ,
        adresse    Varchar (255) ,
        ville      Varchar (255) ,
        secteur    Varchar (255) ,
        labo       Varchar (25) ,
        email      Varchar (25) ,
        PRIMARY KEY (idvisiteur )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: RapportdeVisite
#------------------------------------------------------------

CREATE TABLE RapportdeVisite(
        idrapport     Int NOT NULL ,
        motifvisite   Varchar (255) ,
        bilan         Text ,
        daterapport   Date ,
        idvisiteur    Int ,
        idpraticien   Int ,
        idechantillon Int ,
        PRIMARY KEY (idrapport )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Medicament
#------------------------------------------------------------

CREATE TABLE Medicament(
        idmedicament Int NOT NULL ,
        denomination Varchar (255) ,
        PRIMARY KEY (idmedicament )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Praticien
#------------------------------------------------------------

CREATE TABLE Praticien(
        idpraticien Int NOT NULL ,
        nom         Varchar (255) ,
        prenom      Varchar (25) ,
        PRIMARY KEY (idpraticien )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Echantillon
#------------------------------------------------------------

CREATE TABLE Echantillon(
        idechantillon Int NOT NULL ,
        PRIMARY KEY (idechantillon )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: compose
#------------------------------------------------------------

CREATE TABLE compose(
        quantite      Int ,
        idmedicament  Int NOT NULL ,
        idechantillon Int NOT NULL ,
        PRIMARY KEY (idmedicament ,idechantillon )
)ENGINE=InnoDB;

ALTER TABLE RapportdeVisite ADD CONSTRAINT FK_RapportdeVisite_idvisiteur FOREIGN KEY (idvisiteur) REFERENCES VisiteurMedical(idvisiteur);
ALTER TABLE RapportdeVisite ADD CONSTRAINT FK_RapportdeVisite_idpraticien FOREIGN KEY (idpraticien) REFERENCES Praticien(idpraticien);
ALTER TABLE RapportdeVisite ADD CONSTRAINT FK_RapportdeVisite_idechantillon FOREIGN KEY (idechantillon) REFERENCES Echantillon(idechantillon);
ALTER TABLE compose ADD CONSTRAINT FK_compose_idmedicament FOREIGN KEY (idmedicament) REFERENCES Medicament(idmedicament);
ALTER TABLE compose ADD CONSTRAINT FK_compose_idechantillon FOREIGN KEY (idechantillon) REFERENCES Echantillon(idechantillon);
