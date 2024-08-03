FROM python:3.7-slim

COPY schema.json .

RUN pip install pgsync==2.1.1

CMD ["sh", "-c", "bootstrap --config schema.json && pgsync --config schema.json --daemon"]
