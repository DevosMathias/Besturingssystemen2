<header>
    <h1><!--<span>Tracing App</span>--></h1>
    <nav>
        <ul>
            <li ${param.actual eq 'Home'?"id=actual":""}>
                <a href="Controller">Home</a></li>
            <li ${param.actual eq 'User Overview'?"id=actual":""}>
                <a href="Controller?action=Overview">Overview</a></li>
            <li ${param.actual eq 'Contact Overview'?"id=actual":""}>
                <a href="Controller?action=ContactOverview">Contacts</a></li>
            <li ${param.actual eq 'Register'?"id=actual":""}>
                <a href="Controller?action=Register">Register</a></li>
        </ul>
        <h2>${param.actual}</h2>
    </nav>
</header>
