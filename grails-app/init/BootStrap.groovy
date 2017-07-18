import reservaquadra.*

class BootStrap {

    def init = { servletContext ->

        def adminPapel = Papel.findByAuthority("ROLE_ADMIN") ?:
                new Papel(authority: "ROLE_ADMIN").save()

        def admin = new Usuario(
                username: "admin",
                password: "admin",
                nome: "Administrador",
                enabled : true
        )

        admin.save()
        if (admin.hasErrors()) {
            println admin.errors
        }
        UsuarioPapel.create(admin,adminPapel)

        println 'populando usuÃ¡rio admin - ok'

        def userPapel = Papel.findByAuthority("ROLE_USER")?:
                new Papel(authority: "ROLE_USER").save()

        def ana = new Usuario(
                username: 'ana',
                password: 'ana',
                nome: "Ana",
                enabled: true
        )
        ana.save()
        if (ana.hasErrors()) {
            println ana.errors
        }

        UsuarioPapel.create(ana,userPapel)

        println 'populando usuÃ¡rio ana - ok'

        def bob = new Usuario(
                username: 'bob',
                password: 'bob',
                nome: "Bob",
                enabled: true
        )
        bob.save()
        if (bob.hasErrors()) {
            println bob.errors
        }

        UsuarioPapel.create(bob,userPapel)

        println 'populando usuÃ¡rio bob - ok'

        def quadra1 = new Quadra(numero: 1, esportes: "Futebol")

        quadra1.save()
        if (quadra1.hasErrors()) {
            println quadra1.errors
        }

        println 'populando quadra1 - ok'

        def quadra2 = new Quadra(numero: 2, esportes: "Basquete")

        quadra2.save()
        if (quadra2.hasErrors()) {
            println quadra2.errors
        }

        println 'populando quadra2 - ok'

    }
    def destroy = {
    }
}

