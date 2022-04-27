


/*** Global Variable **********************************************************/


    let headerToggle = document.getElementById('headerToggle');
    let sidebar = document.getElementById('sidebar');
    let main = document.getElementById('main');
    let sideIcons = document.querySelectorAll('.side-icon');
    let windowWidth;


/*** Event ********************************************************************/


    headerToggle.addEventListener('click',setToggle)

    sideIcons.forEach( (e) => {
        e.addEventListener('click', setToggle)
    });

    window.addEventListener('resize', computeWindow);


/*** Function *****************************************************************/


    function setToggle() {
        let isToggled = sidebar.getAttribute('aria-expanded');
        if( isToggled === 'true') {
            openToggle()
        } else {
            closeToggle()
        }
    }

    function computeWindow() {
        console.log("화면 변환중", windowWidth, window.innerWidth)
        if(window.innerWidth < 900) {
            if(window.innerWidth < windowWidth) {
                closeToggle()
                windowWidth = window.innerWidth
            }
        } else {
            windowWidth = 900
        }
    }

    function openToggle() {
        headerToggle.setAttribute('aria-expanded', false);
        sidebar.setAttribute('aria-expanded', false);
        main.setAttribute('aria-expanded', false);
    }

    function closeToggle() {
        headerToggle.setAttribute('aria-expanded', true);
        sidebar.setAttribute('aria-expanded', true);
        main.setAttribute('aria-expanded', true);
    }