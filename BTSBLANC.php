<?php

public function ConsultationFactures(){

	session_start();
	$pdo = PdoSemoctom::getPdoSemoctom();
	$vue= new Smarty();
	
	$action = $_REQUEST['action'];
	
	switch($action){
		
		case 'affiMaisons':{
			$idUsager = $_SESSION['id_usager'];
			$adresses = $pdo->getAdressesUsager($idUsager);

			...
			
		case 'affiFacture':{
			
			$idMaison = $_POST['LaMaison'];
			$factures = $pdo -> getFactureMaison($idMaison);
			$vue->assign('lesFactures', $factures);
			$vue->display('listeFacture.tpl');
			break;		
		}	
	}
}


	public function getFacturesMaison($idMaison){
		
		$req ="SELECT id, an, mois, nomFichier FROM Facture WHERE idHabitation =". $idMaison;
		$res = $pdoSectom::$monpdo->query($req);
		
		$factures = $res->fetchAll();
		return $factures;
	
	}
?>

