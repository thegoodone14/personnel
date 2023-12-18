package personnel;

import java.io.Serializable;
import java.time.LocalDate;
import personnel.ExceptionArrivee;
import personnel.ExceptionDepart;

/**
 * Employé d'une ligue hébergée par la M2L. Certains peuvent 
 * être administrateurs des employés de leur ligue.
 * Un seul employé, rattaché à aucune ligue, est le root.
 * Il est impossible d'instancier directement un employé, 
 * il faut passer la méthode {@link Ligue#addEmploye addEmploye}.
 */

public class Employe implements Serializable, Comparable<Employe>
{
	private static final long serialVersionUID = 4795721718037994734L;
	private String nom, prenom, password, mail;
	private Ligue ligue;
	private GestionPersonnel gestionPersonnel;
	private LocalDate dateArrivee = LocalDate.of(0000, 1, 1);
	private LocalDate dateDepart = LocalDate.of(0000, 1, 1);
	private int id;
	
	Employe(GestionPersonnel gestionPersonnel, Ligue ligue, String nom, String prenom, String mail, String password)
	{
		this.gestionPersonnel = gestionPersonnel;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.mail = mail;
		this.ligue = ligue;
		try {
			this.id = gestionPersonnel.insert(this);
			}
			catch(SauvegardeImpossible e) 
			{
				e.printStackTrace();
			}
	}
	
	/**
	 * Retourne vrai ssi l'employé est administrateur de la ligue 
	 * passée en paramètre.
	 * @return vrai ssi l'employé est administrateur de la ligue 
	 * passée en paramètre.
	 * @param ligue la ligue pour laquelle on souhaite vérifier si this 
	 * est l'admininstrateur.
	 */
	
	public boolean estAdmin(Ligue ligue)
	{
		return ligue.getAdministrateur() == this;
	}
	
	/**
	 * Retourne vrai ssi l'employe est le root.
	 * @return vrai ssi l'employe est le root.
	 */
	
	public boolean estRoot()
	{
		return gestionPersonnel.getRoot() == this;
	}
	
	/**
	 * Retourne le nom de l'employe.
	 * @return le nom de l'employe. 
	 */
	
	public int getID()
	{
		return id;
	}
	
	public GestionPersonnel getGestionPersonnel()
	{
		return gestionPersonnel;
	}
	
	public String getNom()
	{
		return nom;
	}

	/**
	 * Change le nom de l'employe.
	 * @param nom le nouveau nom.
	 */
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}

	/**
	 * Retourne le prénom de l'employe.
	 * @return le prénom de l'employe.
	 */
	
	public String getPrenom()
	{
		return prenom;
	}
	
	/**
	 * Change le prénom de l'employe.
	 * @param prenom le nouveau prénom de l'employe. 
	 */

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	/**
	 * Retourne le mail de l'employe.
	 * @return le mail de l'employe.
	 */
	
	public String getMail()
	{
		return mail;
	}
	
	/**
	 * Change le mail de l'employé.
	 * @param mail le nouveau mail de l'employe.
	 */

	public void setMail(String mail)
	{
		this.mail = mail;
	}

	/**
	 * Retourne vrai ssi le password passé en paramètre est bien celui
	 * de l'employe.
	 * @return vrai ssi le password passé en paramètre est bien celui
	 * de l'employe.
	 * @param password le password auquel comparer celui de l'employe.
	 */
	
	public boolean checkPassword(String password)
	{
		return this.password.equals(password);
	}

	/**
	 * Change le password de l'employe.
	 * @param password le nouveau password de l'employe. 
	 */
	
	public void setPassword(String password)
	{
		this.password= password;
	}

	/**
	 * Retourne la ligue à laquelle l'employe est affecté.
	 * @return la ligue à laquelle l'employe est affecté.
	 */
	
	public Ligue getLigue()
	{
		return ligue;
	}
	
	public void remove()
	{
		Employe root = gestionPersonnel.getRoot();
		if (this != root)
		{
			if (estAdmin(getLigue()))
				getLigue().setAdministrateur(root);
			getLigue().remove(this);
		}
		else
			throw new ImpossibleDeSupprimerRoot();
	}

	@Override
	public int compareTo(Employe autre)
	{
		int cmp = getNom().compareTo(autre.getNom());
		if (cmp != 0)
			return cmp;
		return getPrenom().compareTo(autre.getPrenom());
	}
	
	//Getter de DateArrivee
	public LocalDate getDateArrivee() {
        return dateArrivee;
    }
	
	//Getter de DateDepart
    public LocalDate getDateDepart() {
        return dateDepart;
    }
    
	//Setter pour datearrivee
    public void setDateArrivee(LocalDate dateArrivee) throws ExceptionArrivee{
    		if( (dateDepart != null) && (dateArrivee.isBefore(dateDepart) ) )
    		{
    			throw new ExceptionArrivee();
    		}
    		this.dateArrivee = dateArrivee;
    		try {
    			gestionPersonnel.update(this);
    		}
    		catch (SauvegardeImpossible e)
    		{
    			e.printStackTrace();
    		}
    		
    }
    
    //Setter pour datedepart
    public void setDateDepart(LocalDate dateDepart) throws ExceptionDepart {
    	if( (dateArrivee != null) && (dateDepart.isAfter(dateArrivee) ) )
		{
			throw new ExceptionDepart();
		}
        this.dateDepart = dateDepart;
        try {
			gestionPersonnel.update(this);
		}
		catch (SauvegardeImpossible e)
		{
			e.printStackTrace();
		}
    }
    
	@Override
	public String toString()
	{
		String res = nom + " " + prenom + " " + mail + " (";
		if (estRoot())
			res += "super-utilisateur";
		else
			res += ligue.toString();
		return res + ")";
	}
}


