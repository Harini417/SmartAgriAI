import {
  Box,
  Button,
  Card,
  CardContent,
  Chip,
  Container,
  Grid,
  Stack,
  Typography
} from '@mui/material'
import {
  Agriculture,
  ArrowForward,
  BugReport,
  CheckCircle,
  Cloud,
  Insights,
  Opacity,
  Science,
  Shield,
  Speed,
  TrendingUp
} from '@mui/icons-material'

const features = [
  {
    title: 'Weather Monitoring',
    description: 'Track rainfall, temperature, and wind patterns for better field planning.',
    icon: <Cloud color="success" />
  },
  {
    title: 'Soil Analysis',
    description: 'Assess soil nutrients and moisture to improve crop health and yield.',
    icon: <Science color="success" />
  },
  {
    title: 'Crop Recommendation',
    description: 'Receive AI-driven suggestions for the most suitable crops for your land.',
    icon: <Agriculture color="success" />
  },
  {
    title: 'Smart Irrigation',
    description: 'Optimize water usage with automated irrigation insights and alerts.',
    icon: <Opacity color="success" />
  },
  {
    title: 'Disease Detection',
    description: 'Identify plant diseases quickly with image-based AI support.',
    icon: <BugReport color="success" />
  },
  {
    title: 'Market Price Tracking',
    description: 'Stay ahead with real-time price insights for better harvesting decisions.',
    icon: <TrendingUp color="success" />
  }
]

const benefits = [
  'Actionable intelligence tailored for hilly and remote farming zones.',
  'Improved productivity with lower resource waste and fewer crop losses.',
  'Reliable support for farmers, cooperatives, and agricultural advisors.'
]

const Home = () => (
  <Box sx={{ minHeight: '100vh', background: 'linear-gradient(135deg, #f6fff4 0%, #ecf8e9 100%)' }}>
    <Box sx={{ py: { xs: 8, md: 10 } }}>
      <Container maxWidth="lg">
        <Grid container spacing={6} alignItems="center">
          <Grid item xs={12} md={7}>
            <Stack spacing={3}>
              <Chip
                label="AI-Powered Smart Agriculture Platform"
                color="success"
                sx={{ alignSelf: 'flex-start', fontWeight: 600, px: 1 }}
              />
              <Typography
                variant="h2"
                sx={{ fontWeight: 800, color: '#1b5e20', lineHeight: 1.15, fontSize: { xs: '2rem', md: '3.2rem' } }}
              >
                AI-Powered Smart Agriculture for Efficient Cultivation in Hilly Regions
              </Typography>
              <Typography variant="h6" color="text.secondary" sx={{ maxWidth: 700 }}>
                SmartAgriAI helps farmers make smarter decisions with real-time weather insights,
                soil intelligence, crop planning, irrigation guidance, and disease detection.
              </Typography>
              <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2}>
                <Button
                  variant="contained"
                  size="large"
                  color="success"
                  endIcon={<ArrowForward />}
                  sx={{ borderRadius: 3, px: 3, py: 1.2 }}
                >
                  Get Started
                </Button>
                <Button
                  variant="outlined"
                  size="large"
                  color="success"
                  sx={{ borderRadius: 3, px: 3, py: 1.2 }}
                >
                  View Dashboard
                </Button>
              </Stack>
            </Stack>
          </Grid>

          <Grid item xs={12} md={5}>
            <Box
              sx={{
                background: 'linear-gradient(135deg, #2e7d32 0%, #66bb6a 100%)',
                borderRadius: 4,
                p: 4,
                color: 'white',
                boxShadow: 6
              }}
            >
              <Stack spacing={2}>
                <Typography variant="h5" fontWeight={700}>
                  Transform Farming with Intelligence
                </Typography>
                <Typography variant="body1">
                  Monitor your fields, protect your crops, and increase yields with a connected agricultural platform.
                </Typography>
                <Stack spacing={1.2}>
                  <Box display="flex" alignItems="center" gap={1}>
                    <Shield />
                    <Typography variant="body2">Smart recommendations for every season</Typography>
                  </Box>
                  <Box display="flex" alignItems="center" gap={1}>
                    <Speed />
                    <Typography variant="body2">Fast insights for timely field action</Typography>
                  </Box>
                  <Box display="flex" alignItems="center" gap={1}>
                    <Insights />
                    <Typography variant="body2">Data-driven decisions that scale with your farm</Typography>
                  </Box>
                </Stack>
              </Stack>
            </Box>
          </Grid>
        </Grid>
      </Container>
    </Box>

    <Container maxWidth="lg" sx={{ py: 6 }}>
      <Typography variant="h4" fontWeight={700} color="#1b5e20" textAlign="center" gutterBottom>
        Smart Solutions for Modern Farming
      </Typography>
      <Typography variant="body1" color="text.secondary" textAlign="center" sx={{ mb: 4 }}>
        Discover the intelligent tools that support healthier crops and better farm outcomes.
      </Typography>
      <Grid container spacing={3}>
        {features.map((feature) => (
          <Grid item xs={12} sm={6} md={4} key={feature.title}>
            <Card sx={{ height: '100%', borderRadius: 3, boxShadow: 3, transition: '0.2s', '&:hover': { transform: 'translateY(-4px)', boxShadow: 6 } }}>
              <CardContent>
                <Box sx={{ mb: 1.5 }}>{feature.icon}</Box>
                <Typography variant="h6" fontWeight={700} gutterBottom>
                  {feature.title}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  {feature.description}
                </Typography>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Container>

    <Box sx={{ py: 6, backgroundColor: '#f0f9eb' }}>
      <Container maxWidth="lg">
        <Grid container spacing={4} alignItems="center">
          <Grid item xs={12} md={6}>
            <Typography variant="h4" fontWeight={700} color="#1b5e20" gutterBottom>
              Why Choose SmartAgriAI?
            </Typography>
            <Typography variant="body1" color="text.secondary" sx={{ mb: 2 }}>
              Built for the realities of modern agriculture, SmartAgriAI combines advanced analytics with practical farming support.
            </Typography>
            <Stack spacing={1.5}>
              {benefits.map((item) => (
                <Box key={item} display="flex" alignItems="center" gap={1.2}>
                  <CheckCircle color="success" />
                  <Typography variant="body1">{item}</Typography>
                </Box>
              ))}
            </Stack>
          </Grid>
          <Grid item xs={12} md={6}>
            <Box
              sx={{
                backgroundColor: 'white',
                borderRadius: 4,
                p: 3,
                boxShadow: 3,
                border: '1px solid #dcefe1'
              }}
            >
              <Typography variant="h6" fontWeight={700} gutterBottom>
                Built for resilience and growth
              </Typography>
              <Typography variant="body2" color="text.secondary">
                From hillside farms to expanding agri-businesses, our platform delivers dependable intelligence for every stage of cultivation.
              </Typography>
            </Box>
          </Grid>
        </Grid>
      </Container>
    </Box>

    <Box sx={{ py: 4, backgroundColor: '#1b5e20', color: 'white' }}>
      <Container maxWidth="lg">
        <Stack direction={{ xs: 'column', sm: 'row' }} justifyContent="space-between" alignItems={{ xs: 'flex-start', sm: 'center' }} spacing={1}>
          <Typography variant="body1" fontWeight={600}>
            © 2026 SmartAgriAI. Empowering smarter agriculture.
          </Typography>
          <Typography variant="body2" color="rgba(255,255,255,0.85)">
            Weather • Soil • Crops • Irrigation • Disease Insights
          </Typography>
        </Stack>
      </Container>
    </Box>
  </Box>
)

export default Home
