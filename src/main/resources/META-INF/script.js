// Function to fetch employees from the backend
function fetchEmployees() {
    fetch('/api/employees')
    .then(response => response.json())
    .then(data => {
        const tbody = document.querySelector('#employeeTable tbody');
        tbody.innerHTML = '';

        // Loop through the data and append rows to the table
        data.forEach(employee => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${employee.id}</td>
                <td>${employee.firstName}</td>
                <td>${employee.lastName}</td>
                <td>${employee.email}</td>
                <td><button onclick="editEmployee(${employee.id})">Edit</button> <button onclick="deleteEmployee(${employee.id})">Delete</button></td>
            `;
            tbody.appendChild(tr);
        });
    })
    .catch(error => console.error('Error fetching employees:', error));
}

// Function to add or update an employee
function addOrUpdateEmployee(event) {
    event.preventDefault();
    const employeeId = document.getElementById('employeeId').value;
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const email = document.getElementById('email').value;

    const method = employeeId ? 'PUT' : 'POST';
    let url = '/api/employees';
    if (method === 'PUT') {
        url += `/${employeeId}`;
    }

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ id: employeeId, firstName: firstName, lastName: lastName, email: email })
    })
    .then(response => {
        if (response.ok) {
            fetchEmployees();
            document.getElementById('employeeForm').reset();
        } else {
            console.error('Error adding/updating employee:', response.statusText);
        }
    })
    .catch(error => console.error('Error adding/updating employee:', error));
}

// Function to edit an employee
function editEmployee(employeeId) {
    fetch(`/api/employees/${employeeId}`)
    .then(response => response.json())
    .then(employee => {
        document.getElementById('employeeId').value = employee.id;
        document.getElementById('firstName').value = employee.firstName;
        document.getElementById('lastName').value = employee.lastName;
        document.getElementById('email').value = employee.email;
    })
    .catch(error => console.error('Error editing employee:', error));
}

// Function to delete an employee
function deleteEmployee(employeeId) {
    fetch(`/api/employees/${employeeId}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            fetchEmployees();
        } else {
            console.error('Error deleting employee:', response.statusText);
        }
    })
    .catch(error => console.error('Error deleting employee:', error));
}

// Initial fetch of employees when the page loads
fetchEmployees();

// Event listener for form submission
document.getElementById('employeeForm').addEventListener('submit', addOrUpdateEmployee);
