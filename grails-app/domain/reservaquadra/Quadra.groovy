package reservaquadra

class Quadra {

    static hasMany = [reservas: Reserva]

    static constraints = {
        numero (blank: false)
        esportes (blank: false)
    }

    int numero
    String esportes

    String toString(){
        return "Quadra " + numero
    }
}
