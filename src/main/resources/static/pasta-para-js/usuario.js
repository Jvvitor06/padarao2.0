const API_USUARIO = "https://zika-krry.onrender.com/usuario";

// ----------------- CADASTRAR -----------------
document.getElementById("formUsuario").addEventListener("submit", async function (e) {
    e.preventDefault();

    const auth = await pedirCredenciais();
    if (!auth) return alert("Cadastro cancelado.");

    const usuario = {
        nome: document.getElementById("nome").value,
        cpf: document.getElementById("cpf").value,
        dataNascimento: document.getElementById("dataNascimento").value,
        email: document.getElementById("email").value,
        telefone: document.getElementById("telefone").value
    };

    try {
        const response = await fetch(API_USUARIO, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Basic " + auth
            },
            body: JSON.stringify(usuario)
        });

        if (response.ok) {
            alert("Usuário cadastrado com sucesso!");
            document.getElementById("formUsuario").reset();
            carregarUsuarios();
        } else if (response.status === 401) {
            alert("Usuário ou senha inválidos.");
        } else {
            const errorText = await response.text();
            alert("Erro: " + errorText);
        }
    } catch (err) {
        console.error(err);
        alert("Erro de conexão.");
    }
});

// ----------------- LISTAR (público) -----------------
async function carregarUsuarios() {
    try {
        const res = await fetch(API_USUARIO); // sem auth
        if (!res.ok) throw new Error(`Erro ${res.status}`);
        const usuarios = await res.json();

        const div = document.getElementById("listaUsuarios");
        div.innerHTML = usuarios.map(u =>
            `<p>${u.nome} - ${u.cpf} 
                <button onclick="deletarUsuario('${u.cpf}')">Excluir</button>
            </p>`
        ).join("");

    } catch (err) {
        console.error(err);
        alert("Falha ao carregar usuários");
    }
}

// ----------------- DELETAR -----------------
async function deletarUsuario(cpf) {
    if (!confirm("Tem certeza que deseja deletar este usuário?")) return;

    const auth = await pedirCredenciais();
    if (!auth) return alert("Ação cancelada.");

    try {
        const res = await fetch(`${API_USUARIO}?cpf=${cpf}`, {
            method: "DELETE",
            headers: {
                "Authorization": "Basic " + auth
            }
        });

        if (!res.ok) throw new Error(`Erro ${res.status}`);
        carregarUsuarios(); // atualiza lista
    } catch (err) {
        console.error(err);
        alert("Falha ao deletar usuário");
    }
}

// ----------------- FUNÇÃO PEDIR CREDENCIAIS -----------------
async function pedirCredenciais() {
    const username = prompt("Digite seu usuário admin:");
    if (!username) return null;
    const password = prompt("Digite sua senha:");
    if (!password) return null;
    return btoa(username + ":" + password);
}

// ----------------- AUTOLOAD -----------------
document.addEventListener("DOMContentLoaded", carregarUsuarios);
