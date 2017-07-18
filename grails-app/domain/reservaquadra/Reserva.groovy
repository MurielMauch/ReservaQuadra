package reservaquadra

class Reserva {

    static belongsTo = [usuario: Usuario]

    static constraints = {
        horario(blank: false)
        reservaAtual(blank: false)
        ordenador(blank: false)
    }

    Date horario
    boolean reservaAtual
    int ordenador

    Quadra quadra

    String toString(){
        return quadra + " " + usuario + " " + horario + " " + reservaAtual
    }
}
