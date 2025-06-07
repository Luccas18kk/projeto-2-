import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CadastroServlet")
public class CadastroServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        
        String senha = request.getParameter("senha");
        String confirmarSenha = request.getParameter("confirmarSenha");
        String cep = request.getParameter("cep");
        String rua = request.getParameter("rua");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String numeroCartao = request.getParameter("numero_cartao");
        String validade = request.getParameter("validade");

        
        if (senha == null || !senha.equals(confirmarSenha)) {
            request.setAttribute("erro", "Senhas não coincidem!");
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
            return;
        }

        
        if (cpf == null || cpf.isEmpty() || cep == null || cep.isEmpty()) {
            request.setAttribute("erro", "Campos obrigatórios não preenchidos!");
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
            return;
        }

       

        
        request.getSession().setAttribute("mensagem", "Cadastro realizado com sucesso!");
        response.sendRedirect("login.html");
    }
}
