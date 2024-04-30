function LoginForm() {
    return (
    // <form onSubmit={handleSubmit}>
        <div>
            <form>
                <label htmlFor="username">Username: </label>< br/>
                <input
                type="text"
                // value={username}
                placeholder="Enter a username"
                // onChange={({ target }) => setUsername(target.value)}
                />< br/>
                <label htmlFor="username">Email: </label>< br/>
                <input
                type="text"
                // value={username}
                placeholder="Enter your email address"
                // onChange={({ target }) => setEmail(target.value)}
                />< br/>
                <label htmlFor="password">Password: </label>< br/>
                <input
                    type="password"
                //   value={password}
                    placeholder="Enter a strong password"
                //   onChange={({ target }) => setPassword(target.value)}
                />< br/>
                <button type="submit">Login</button>
            </form>
        </div>
    )
}
  
export default LoginForm