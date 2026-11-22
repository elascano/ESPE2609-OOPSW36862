import express, { Request, Response } from 'express';

const app = express();
const port = 4006;

app.get('/', (req: Request, res: Response) => {
    res.send('Welcome to my server, Julián server');
});

app.listen(port, () => {
    console.log(`Julián's Server is running at http://127.0.0.1:${port}`);
});