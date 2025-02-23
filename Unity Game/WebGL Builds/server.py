import http.server
import socketserver
import mimetypes

class GzipHTTPRequestHandler(http.server.SimpleHTTPRequestHandler):
    def guess_type(self, path):
        """Ensure the correct MIME type is returned."""
        if path.endswith(".gz"):
            return mimetypes.guess_type(path[:-3])[0] or "application/octet-stream"
        return super().guess_type(path)

    def end_headers(self):
        """Add the correct Content-Encoding header for .gz files."""
        if self.path.endswith(".gz"):
            self.send_header("Content-Encoding", "gzip")
            self.send_header("Cache-Control", "no-cache")
        super().end_headers()

PORT = 8000
Handler = GzipHTTPRequestHandler

with socketserver.TCPServer(("", PORT), Handler) as httpd:
    print(f"Serving on port {PORT}")
    httpd.serve_forever()