// let rolesSelect = document.getElementById("rolesEditModal")

allRolesSelect = document.getElementsByName("roles")
for (const rolesSelect of allRolesSelect) {
    getRoles(rolesSelect)
}

function getRoles (rolesModal){
    fetch('/roles')
        .then(response => response.json())
        .then(roles => {
            for (const role of roles) {
                rolesModal.append(new Option(role.name, role.id));
            }
        })
        .catch(error => console.error('Ошибка:', error));
}
// fetch('/roles')
//     .then(response => response.json())
//     .then(roles => {
//         for (const role of roles) {
//             rolesSelect.append(new Option(role.name, role.id));
//         }
//     })
//     .catch(error => console.error('Ошибка:', error));
