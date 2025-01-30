import java.math.RoundingMode

class Persona1(var peso: Double,var altura: Double) {
    constructor(nombre: String, peso: Double, altura: Double): this(peso, altura){
        this.nombre = nombre
    }

    var nombre: String = "Desconocido"

    private val imc = (peso/(altura * altura)).toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()

    override fun toString(): String {
        return "Persona: $nombre, Peso: $peso, Altura: $altura, IMC: $imc%"
    }

    override fun equals(other: Any?):Boolean {
        if (other !is Persona1) {return false}
        if (this === other) {return true}

        return (this.nombre == other.nombre &&  this.altura == other.altura &&  this.peso == other.peso)
    }

    private fun alturaEncimaMedia(altura: Double): Boolean{
        return if (altura >= 1.75){
            true
        } else {
            false
        }
    }

    private fun pesoEncimaMedia(peso: Double): Boolean{
        return if (peso >= 70.0) {
            true
        } else {
            false
        }
    }

    private fun obtenerDescImc():String {
        return if (imc < 18.5) {
            "Bajo peso"
        } else if (imc in 18.5..24.9) {
            "Peso saludable"
        } else if (imc in 25.0..29.9) {
            "Sobrepeso"
        } else {
            "Obesidad"
        }
    }

    fun saludar():String {
        return "Hola, me llamo $nombre"
    }
    fun obtenerDesc():String{
        val mensaje: String = if (alturaEncimaMedia(altura) && pesoEncimaMedia(peso)){
            "$nombre: mide $altura m y esta sobre la media, pesa $peso Kg y tambien esta sobre la media y su IMC es del $imc% por lo cual tiene ${obtenerDescImc()}."
        } else if (alturaEncimaMedia(altura) && !pesoEncimaMedia(peso)){
            "$nombre: mide $altura m y esta sobre la media, tambien pesa $peso Kg y no esta sobre la media y su IMC es del $imc% por lo cual tiene ${obtenerDescImc()}."
        } else if (!alturaEncimaMedia(altura) && pesoEncimaMedia(peso)) {
            "$nombre: mido $altura m y no esta sobre la media, tambien pesa $peso Kg y esta sobre la media y su IMC es del $imc% por lo cual tiene ${obtenerDescImc()}."
        } else {
            "$nombre: mido $altura m y no esta sobre la media, tambien pesa $peso Kg y tampoco esta sobre la media y su IMC es del $imc% por lo cual tiene ${obtenerDescImc()}."
        }
        return mensaje
    }

}


fun main() {

    val persona1 = Persona1("Adrian", 100.0, 1.90)
    val persona2 = Persona1("Gonzalo", 60.0, 1.84)
    val persona3 = Persona1("Gonzalo", 60.0, 1.83)
    val persona4 = Persona1("Manolo", 75.48, 1.74)
    val persona5 = Persona1("Juan", 95.4, 1.23)
    val persona6 = Persona1("Diego", 42.5, 1.95)
    val persona7 = Persona1("Pedro", 50.4, 1.43)

    val personas = listOf(persona1, persona2, persona3, persona4, persona5, persona6, persona7)

    for (persona in personas){
        println(persona.saludar())
        println(persona.obtenerDesc())
    }

}