document.querySelectorAll('.job-item-header').forEach(button => {
    button.addEventListener('click', () => {
        const item = button.closest('.job-item');
        item.classList.toggle('active');
    });
});