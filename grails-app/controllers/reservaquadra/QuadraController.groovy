package reservaquadra

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class QuadraController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Quadra.list(params), model:[quadraCount: Quadra.count()]
    }

    def show(Quadra quadra) {
        respond quadra
    }

    def create() {
        respond new Quadra(params)
    }

    @Transactional
    def save(Quadra quadra) {
        if (quadra == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (quadra.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond quadra.errors, view:'create'
            return
        }

        quadra.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'quadra.label', default: 'Quadra'), quadra.id])
                redirect quadra
            }
            '*' { respond quadra, [status: CREATED] }
        }
    }

    def edit(Quadra quadra) {
        respond quadra
    }

    @Transactional
    def update(Quadra quadra) {
        if (quadra == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (quadra.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond quadra.errors, view:'edit'
            return
        }

        quadra.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'quadra.label', default: 'Quadra'), quadra.id])
                redirect quadra
            }
            '*'{ respond quadra, [status: OK] }
        }
    }

    @Transactional
    def delete(Quadra quadra) {

        if (quadra == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        quadra.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'quadra.label', default: 'Quadra'), quadra.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'quadra.label', default: 'Quadra'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
