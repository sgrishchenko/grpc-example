import {HelloRequest} from 'proto/hello_pb'
import {SimpleClient} from 'proto/HelloServiceClientPb'

const client = new SimpleClient('http://localhost:8080');

const request = new HelloRequest();
request.setName('World');

client.sayHello(request, {}, (err, response) => {
    if (err) throw err;
    console.log(response.getMessage());
});

client.streamHello(request)
    .on('data', response => console.log(response.getMessage()))
    .on('end', () => console.log('done'))
    .on('error', err => { throw err })