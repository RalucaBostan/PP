import kotlin.properties.Delegates

class Birth(val year: Int, val Month: Int, val Day: Int){
    override fun toString() : String{
        return "($Day.$Month.$year)"
    }
}

class Contact(val Name: String, val Phone: String, val BirthDate: Birth){
    override fun toString(): String {
        return "Name: $Name, Mobile: $Phone, Date: $BirthDate"
    }

    fun equals(contact: Contact) : Boolean{
        return this.Name.equals(contact.Name) && this.Phone.equals(contact.Phone)
    }
}


class Agenda(val NumeAgenda : String){
    var agenda : MutableList<Contact>  = mutableListOf()

    var deleteDelegate = DeletingAPI(this.agenda)

    var searchingDelegate = SearchingAPI(this.agenda)

    fun adaugaContact(contact : Contact){
        this.agenda.add(contact)
    }

    fun AfisareAgenda(){
        agenda.forEach(::println)
        println("\n")
    }


}

class DeletingAPI(var agenda : MutableList<Contact>){
    fun eliminaContactdupaNume(string: String){
        agenda.forEach{
            if(it.Name.equals(string)){
                agenda.remove(it)
                return
            }
        }
    }

    fun eliminaContactdupaNrTelefon(string: String){
        agenda.filter{!it.Phone.equals(string)}
    }

    fun eliminareDupaPozitie(index:Int){
        agenda.removeAt(index)
    }

    fun eliminaContact(contact : Contact){
        for(con in agenda){
            if(con.equals(contact)){
                agenda.remove(con)
            }
        }
    }
}


class SearchingAPI(var agenda : MutableList<Contact>){
    fun cautaContactdupaNume(string: String) : Contact?{
        for(contact in agenda){
            if(contact.Name.equals(string)){
                return contact
            }
        }
        return null
    }

    fun cautaContactdupaNrTelefon(string: String) : Contact?{
        for(contact in agenda){
            if(contact.Phone.equals(string)){
                return contact
            }
        }
        return null
    }

    fun cautaeDupaPozitie(index:Int) : Contact?{
        if(agenda.size > index) {
            return agenda.get(index)
        }
        else{
            print("Pozitia nu se gaseste in agenda")
        }
        return null
    }

}


fun main(args : Array<String>) {
    val agenda = Agenda("Falticeni")

    agenda.adaugaContact(Contact("Mihai", "0744321987", Birth(1900, 11, 25)))
    agenda.adaugaContact(Contact("George", "0761332100", Birth(2002, 3, 14)))
    agenda.adaugaContact(Contact("Liviu", "0231450211", Birth(1999, 7, 30)))
    agenda.adaugaContact(Contact("Popescu", "0211342787", Birth(1955, 5, 12)))


    agenda.AfisareAgenda()

    println("Cautam contactul Liviu  :  Am gasit ${agenda.searchingDelegate.cautaContactdupaNume("Liviu")}")

    println("Cautam contactul 0211342787  :  Am gasit ${agenda.searchingDelegate.cautaContactdupaNrTelefon("0211342787")}\n\n")

    println("Agenda dupa eliminare contact [George]:")

    agenda.deleteDelegate.eliminareDupaPozitie(1)

    agenda.AfisareAgenda()

    agenda.deleteDelegate.eliminaContact(Contact("Liviu", "0231450211", Birth(1999, 7, 30)))

    println("Agenda dupa eliminare contact [Liviu]:")

    agenda.AfisareAgenda()

    agenda.deleteDelegate.eliminaContactdupaNume("Popescu")

    println("Agenda dupa eliminare contact Popescu")

    agenda.AfisareAgenda()
}
