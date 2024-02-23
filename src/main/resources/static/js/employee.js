const tBody = document.getElementById("tBody");
const paging = document.getElementById("paging");
const form = document.getElementById('form');
const formBody = document.getElementById('formBody');
let employeeSelected = {};
let page = 0;
let employees;
let groups = [];
let searchValue = '';
let totalPage = 0;
const LIMIT = 2;


form.onsubmit = async (e) => {
    console.log("aaaaa")
    e.preventDefault();
    let data = getDataFromForm(form);
    data = {
        ...data,
        }


    let result = false;
    if (employeeSelected.id) {
        result = await myFetch({data, url: '/api/employees/' + employeeSelected.id, method: 'PUT', message: 'Edited'});

    } else {
        result = await myFetch({data, url: '/api/employees', method: 'POST', message: 'Created'})
    }
    if (result) {

        await renderTable();
        $('#staticBackdrop').modal('hide');
    }
}


// get data api employee + group
async function getGroups() {
    const res = await fetch('/api/groups')
    return await res.json();
}

async function getEmployees() {
    const res = await fetch(`/api/employees?page=${page}&size=${LIMIT}&search=${searchValue}`)
    return await res.json();
}

// render tBody

function renderTBody(items) {
    let str = '';
    items.forEach((e, i) => {
        str += renderItemStr(e, i);
    })
    tBody.innerHTML = str;
}

// render item

function renderItemStr(e, index) {
    return `<tr>
                    <td>
                        ${(page * LIMIT) + index + 1}
                    </td>
                    <td>
                        ${e.group.name}
                    </td>
                    <td>
                        ${e.name}
                    </td>
                    <td>
                        ${e.gender ? 'Male' : 'Female'}
                    </td>
                    <td>
                        ${e.phoneNum}
                    </td>
                     
                    <td>
                        <a class="btn btn-primary text-white  edit" data-id="${e.id}" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Edit</a>
                        <a class="btn btn-warning text-white delete" data-id="${e.id}">Delete</a>
                    </td>
                </tr>`
}

// render paging
function renderPaging() {
    paging.innerHTML = '';
    let str = `<li class="page-item ${+page === 0 ? 'disabled' : ''}">
      <a class="page-link" tabindex="-1" aria-disabled="true" data-page="${page - 1}">Previous</a>
    </li>`;
    for(let i = 0; i < totalPage; i++){
        str += `<li class="page-item ${+page === i ? 'active' : ''}"><a class="page-link" data-page="${i}">${i + 1}</a></li>`
    }
    str += `<li class="page-item ${+page === totalPage - 1 ? 'disabled' : ''}">
      <a class="page-link"   data-page="${(+page) + 1}">Next</a>
    </li>`;
    paging.innerHTML = str;

    const pages = document.querySelectorAll('.page-link');
    for (let i = 0; i < pages.length; i++) {
        pages[i].addEventListener('click', async () => {
            let newPage = +pages[i].dataset.page;
            if(newPage < 0 || newPage > totalPage - 1) return;
            page = newPage;
            page = pages[i].dataset.page;
            await renderTable();
        })
    }
}

function showCreate() {
    $('#staticBackdropLabel').text('Create Employee');
    clearForm();
    renderForm(formBody, getDataInput())
}

function getDataInput() {
    return [
        {
            label: 'Name',
            name: 'name',
            value: employeeSelected.name,
            required: true,
            // pattern: "^[A-Za-z 0-9]{6,20}",
            message: "Name must have minimum is 6 characters and maximum is 20 characters",
        },
        {
            label: 'Group',
            name: 'groupId',
            value: employeeSelected.groupId,
            type: 'select',
            required: true,
            options: groups,
            message: 'Please choose group'
        },
        {
            label: 'Dob',
            name: 'dob',
            value: employeeSelected.dob,
            // pattern: "\\d{4}-\\d{2}-\\d{2}",
            message: 'Enter the correct date of birth',
            required: true,
            type: 'date'
        },
        {
            label: 'PhoneNumber',
            name: 'phoneNum',
            value: employeeSelected.phoneNum,
            // pattern: "^(\\+?84|0)(1\\d{9}|3\\d{8}|5\\d{8}|7\\d{8}|8\\d{8}|9\\d{8})$",
            message: "Enter the correct phone number",
        },
        {
            label: 'Email',
            name: 'email',
            value: employeeSelected.email,
            // pattern: "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$",
            message: "Enter the correct email",
            required: true,
            type: 'email'
        },
        {
            label: 'Gender',
            name: 'gender',
            value: employeeSelected.gender,
            type: 'radio',
            required: true,
            options: [
                {
                    name: 'Male',
                    value: true
                },
                {
                    name: 'Female',
                    value: false
                }
            ],
            message: 'Please choose gender'
        },
        {
            label: 'CityCard',
            name: 'cityCard',
            value: employeeSelected.cityCard,
            required: true,
            message: 'Please input city card'
        },
        {
            label: 'Address',
            name: 'address',
            value: employeeSelected.address,
            required: true,
            message: 'Please input address'
        }
    ];
}

window.onload = async () => {
    groups = await getGroups();
    await renderTable()
    renderForm(formBody, getDataInput());
}

async function renderTable() {
    const pageable = await getEmployees();
    employees = pageable.content;
    totalPage = pageable.totalPages;
    renderTBody(employees);
    await addEventEditAndDelete();
    renderPaging();
}

const addEventEditAndDelete = async () => {
    const eEdits = tBody.querySelectorAll('.edit');
    const eDeletes = tBody.querySelectorAll('.delete');
    for (let i = 0; i < eEdits.length; i++) {
        eEdits[i].addEventListener('click', () => {
            showEdit(eEdits[i].dataset.id);
        })
        eDeletes[i].addEventListener('click', () => {
            deleteProduct(eDeletes[i].dataset.id);
        })
    }
}

function deleteProduct(id) {
    if (confirm('Do you want remove it?')) {
        fetch('/api/employees/' + id, {method: 'DELETE'})
            .then(async () => {
                await renderTable();
                toastr.success('Delete success');
            })
    }
}

async function showEdit(id) {
    $('#staticBackdropLabel').text('Edit Employee');
    clearForm();
    employeeSelected = await findEmployeeById(id);
    console.log(employeeSelected)
    renderForm(formBody, getDataInput());
}

async function findEmployeeById(id) {
    const res = await fetch('/api/employees/' + id);
    return await res.json();
}

function clearForm() {
    form.reset();
    employeeSelected = {};
}

