
const genereateRandomColor = () => {
    const red = Math.floor(Math.random() * 100) + 50;
    const green = Math.floor(Math.random() * 100) + 50;
    const blue = Math.floor(Math.random() * 100) + 50;

    return `rgb(${red}, ${green}, ${blue})`;
}

const Error = ({ onClick }) => {
    return (
        <div className="error-container">
            <p className="error-message">Ops, parece que ocorreu um erro! Tente novamente.</p>
            <button onClick={onClick} className="button retry-button">Tentar Novamente</button>
        </div>
    );
};

const Loading = () => {
    return (
        <div className="loading-container">
            <div className="loading-spinner"></div>
        </div>
    );
};


const useFecthQuotes = () => {

    const [response, setResponse] = React.useState({
        data: null,
        isLoading: false,
        error: null
    })

    const fetchData = async () => {

        setResponse(previous => ({
            ...previous,
            isLoading: true,
            data: null,
            error: null
        }))

        try {
            const response = await fetch("https://type.fit/api/quotes", { method: "GET" })
            const json = await response.json()
            const mappedQuotes = json.map(quote => {
                return {
                    ...quote,
                    color: genereateRandomColor()
                }
            })

            const randomQuote = mappedQuotes[~~(Math.random() * mappedQuotes.length)]

            await new Promise(resolve => setTimeout(resolve, 0.5 * 1000))

            setResponse(previous => {
                return {
                    ...previous,
                    isLoading: false,
                    data: randomQuote
                }
            })

        } catch (e) {
            setResponse(previous => {

                return {
                    ...previous,
                    isLoading: false,
                    error: e
                }

            })
        }

    }

    return [response, fetchData]

}

const Main = () => {
    const [currentResponse, handleFetchQuotes] = useFecthQuotes();

    React.useEffect(() => {
        handleFetchQuotes();
    }, [])

    if (currentResponse.data) {
        document.body.style.backgroundColor = currentResponse.data.color;
    }

    return (
        <main id="quote-box">
            {currentResponse.error && <Error onClick={handleFetchQuotes} />}
            {currentResponse.isLoading && <Loading />}
            {currentResponse.data && (

                <div className="box">
                    <h1 id="title">Quote do Dia</h1>

                    <div className="quote">

                        <span
                            style={{ color: currentResponse.data.color }}
                            id="text">
                            <i className="fa fa-quote-left"> </i>
                            {currentResponse.data.text}
                        </span>

                        <div className="quote-author">
                            <span
                                style={{ color: currentResponse.data.color }}
                                className="author decoration">-</span>

                            <span
                                style={{ color: currentResponse.data.color }}
                                id="author" className="author">{currentResponse.data.author}
                            </span>
                        </div>

                    </div>

                    <div className="buttons">

                        <div className="socials">

                            <a
                                style={{ backgroundColor: currentResponse.data.color }}
                                className="button"
                                id="tweet-button"
                                target="_top"
                                href={`https://twitter.com/intent/tweet?hashtags=quotes&related=freecodecamp&text=${currentResponse.data.text}`}
                            >

                                <i className="fa fa-twitter"></i>

                            </a>

                        </div>

                        <button
                            style={{ backgroundColor: currentResponse.data.color }}
                            id="new-quote"
                            className="button"
                            onClick={handleFetchQuotes}
                        >Novo Quote
                        </button>

                    </div>
                </div>
            )}

        </main>
    )



}

const App = () => {
    return (
        <>
            <Main />
        </>
    )
}

ReactDOM.render(<App />, document.getElementById('app'));